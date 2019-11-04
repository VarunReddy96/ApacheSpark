package edu.rit.cs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import scala.Tuple2;
import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordCount_Spark {

    public static final String OutputDirectory = "dataset/review-outputs";
    public static final String DatasetFile = "dataset/amazon-reviews.csv";

    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null)
            for (File file : allContents)
                deleteDirectory(file);
        return directoryToBeDeleted.delete();
    }

    public static void wordcount_1(JavaSparkContext jsc) {
        JavaRDD<String> textFile = jsc.textFile("word_count/"+DatasetFile);
        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);

        deleteDirectory(new File("word_count/"+OutputDirectory+"_1"));
        counts.saveAsTextFile("word_count/"+OutputDirectory+"_1");
    }


    public static void wordcount_2(SparkSession spark) {
        Dataset ds = spark.read()
                .option("header", "true")
                .option("delimiter", ",")
                .option("inferSchema", "true")
                .csv("word_count/"+DatasetFile);

        ds = ds.withColumn("Time", ds.col("Time").cast(DataTypes.IntegerType))
                .withColumn("Score", ds.col("Score").cast(DataTypes.IntegerType))
                .withColumn("HelpfulnessNumerator", ds.col("HelpfulnessNumerator").cast(DataTypes.IntegerType))
                .withColumn("HelpfulnessDenominator", ds.col("HelpfulnessDenominator").cast(DataTypes.IntegerType))
                .withColumn("Id", ds.col("Id").cast(DataTypes.IntegerType));

        // Encoders are created for Java beans
        Encoder<AmazonFineFoodReview> reviewEncoder = Encoders.bean(AmazonFineFoodReview.class);
        Dataset<AmazonFineFoodReview> ds1 = ds.as(reviewEncoder);
        ds1.show();

        // extract one column
        Dataset<String> ds2 = ds1.select(ds1.col("Summary")).as(Encoders.STRING());
        ds2.show();

        JavaRDD<String> rdd1 = ds2.toJavaRDD().flatMap(s -> {
            if (s!=null) // s can be null, we need to check!
                return Arrays.asList(s.split(" ")).iterator();
            else
                return Arrays.asList("").iterator();
        });

        // debug message for printing all words
//        rdd1.foreach(w -> {
//            System.out.println(w+" ");
//        });

        JavaPairRDD<String, Integer> kv_pair= rdd1.mapToPair(w -> new Tuple2<>(w, 1));

        // debug message for printing kv pair
        kv_pair.foreach(kv -> {
            System.out.println(kv._1()+" "+kv._2());
        });

        kv_pair = kv_pair.reduceByKey((a, b) -> a+b);
        // debug message for printing the first kv pair
//        Tuple2<String, Integer> t = kv_pair.first();
//        System.out.println(t._1() + " " + t._2());

        deleteDirectory(new File("word_count/"+OutputDirectory+"_2"));
        kv_pair.saveAsTextFile("word_count/"+OutputDirectory+"_2");
    }

    public static void wordcount_3(SparkSession spark) {
        Dataset ds = spark.read()
                .option("header", "true")
                .option("delimiter", ",")
                .option("inferSchema", "true")
                .csv("word_count/"+DatasetFile);

        ds = ds.withColumn("Time", ds.col("Time").cast(DataTypes.IntegerType))
                .withColumn("Score", ds.col("Score").cast(DataTypes.IntegerType))
                .withColumn("HelpfulnessNumerator", ds.col("HelpfulnessNumerator").cast(DataTypes.IntegerType))
                .withColumn("HelpfulnessDenominator", ds.col("HelpfulnessDenominator").cast(DataTypes.IntegerType))
                .withColumn("Id", ds.col("Id").cast(DataTypes.IntegerType));

        // Encoders are created for Java beans
        Encoder<AmazonFineFoodReview> reviewEncoder = Encoders.bean(AmazonFineFoodReview.class);
        Dataset<AmazonFineFoodReview> ds1 = ds.as(reviewEncoder);
        ds1.show();

        // extract one column
        Dataset<String> ds2 = ds1.select(ds1.col("Summary")).as(Encoders.STRING());
        ds2.show();

        JavaRDD<String> rdd1 = ds2.toJavaRDD().flatMap(s -> {
            if (s!=null) // s can be null, we need to check!
                return Arrays.asList(s.split(" ")).iterator();
            else
                return Arrays.asList("").iterator();
        });

        JavaRDD<String> rdd2 = rdd1.map(w -> {
            Pattern pattern = Pattern.compile("([a-zA-Z]+)");
            Matcher matcher = pattern.matcher(w);
            if(matcher.find())
                return matcher.group().toLowerCase();
            else
                return "";
        });

        // debug message for printing all words
//        rdd2.foreach(w -> {
//            System.out.println(w+" ");
//        });

        JavaPairRDD<String, Integer> kv_pair= rdd2.mapToPair(w -> new Tuple2<>(w, 1));

        // debug message for printing kv pair
//        kv_pair.foreach(kv -> {
//            System.out.println(kv._1()+" "+kv._2());
//        });

        kv_pair = kv_pair.reduceByKey((a, b) -> a+b);
        // debug message for printing the first kv pair
//        Tuple2<String, Integer> t = kv_pair.first();
//        System.out.println(t._1() + " " + t._2());

        deleteDirectory(new File("word_count/"+OutputDirectory+"_3"));
        kv_pair.saveAsTextFile("word_count/"+OutputDirectory+"_3");
    }


    public static void main(String[] args) throws Exception {
        // Create a SparkConf that loads defaults from system properties and the classpath
        SparkConf sparkConf = new SparkConf();
        sparkConf.set("spark.master", "local[4]");
        //Provides the Spark driver application a name for easy identification in the Spark or Yarn UI
        sparkConf.setAppName("Word Count");

        // Creating a session to Spark. The session allows the creation of the
        // various data abstractions such as RDDs, DataFrame, and more.
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

        // Creating spark context which allows the communication with worker nodes
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

//        wordcount_1(jsc);

//        wordcount_2(spark);

        wordcount_3(spark);

        // Stop existing spark context
        jsc.close();

        // Stop existing spark session
        spark.close();
    }
}
