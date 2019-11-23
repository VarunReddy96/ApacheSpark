package edu.rit.cs;

/*
 * LargeTriangleParallel.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;

import java.io.File;
import java.io.IOException;

public class DiversityIndex {
    public static final String OutputDirectory = "D:/Courses/parallel computing/parallelcomputing/word_count/dataset/review-outputs";
    public static final String DatasetFile = "D:/Courses/parallel computing/parallelcomputing/word_count/dataset/cc-est2017-alldata.csv";

    /**
     * This method deletes the output directory if present.
     *
     * @param directoryToBeDeleted: This deletes the output directory where the final textfile is saved.
     *
     */


    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null)
            for (File file : allContents)
                deleteDirectory(file);
        return directoryToBeDeleted.delete();
    }

    /**
     * This method calculates the Diversity index and oututs the results into a single textfile
     * into an outputdirectory.
     *
     * @param spark: This creates an entry point to programming Spark with the Dataset and DataFrame API. .
     *
     */


    public static void calculateIndex(SparkSession spark) throws IOException {
        Dataset ds = spark.read()
                .option("header", "true")
                .option("delimiter", ",")
                .option("inferSchema", "true")
                .csv(DatasetFile);

        ds = ds.withColumn("STNAME", ds.col("STNAME").cast(DataTypes.StringType))
                .withColumn("CTYNAME", ds.col("CTYNAME").cast(DataTypes.StringType))
                .withColumn("YEAR", ds.col("YEAR").cast(DataTypes.IntegerType))
                .withColumn("AGEGRP", ds.col("AGEGRP").cast(DataTypes.IntegerType))
                .withColumn("WA_MALE", ds.col("WA_MALE").cast(DataTypes.IntegerType))
                .withColumn("WA_FEMALE", ds.col("WA_FEMALE").cast(DataTypes.IntegerType))
                .withColumn("BA_MALE", ds.col("BA_MALE").cast(DataTypes.IntegerType))
                .withColumn("BA_FEMALE", ds.col("BA_FEMALE").cast(DataTypes.IntegerType))
                .withColumn("IA_MALE", ds.col("IA_MALE").cast(DataTypes.IntegerType))
                .withColumn("IA_FEMALE", ds.col("IA_FEMALE").cast(DataTypes.IntegerType))
                .withColumn("AA_MALE", ds.col("AA_MALE").cast(DataTypes.IntegerType))
                .withColumn("AA_FEMALE", ds.col("AA_FEMALE").cast(DataTypes.IntegerType))
                .withColumn("NA_MALE", ds.col("NA_MALE").cast(DataTypes.IntegerType))
                .withColumn("NA_FEMALE", ds.col("NA_FEMALE").cast(DataTypes.IntegerType))
                .withColumn("TOM_MALE", ds.col("TOM_MALE").cast(DataTypes.IntegerType))
                .withColumn("TOM_FEMALE", ds.col("TOM_FEMALE").cast(DataTypes.IntegerType));

        Dataset ds2 = ds.filter(ds.col("AGEGRP").equalTo(0)).select("STNAME", "CTYNAME", "YEAR", "AGEGRP", "WA_MALE", "WA_FEMALE", "BA_MALE", "BA_FEMALE", "IA_MALE", "IA_FEMALE", "AA_MALE", "AA_FEMALE", "NA_MALE", "NA_FEMALE", "TOM_MALE", "TOM_FEMALE").
                groupBy("STNAME", "CTYNAME").sum();

        Dataset ds3 = ds2.withColumn("WA", ds2.col("sum(WA_MALE)").plus(ds2.col("sum(WA_FEMALE)")))
                .withColumn("BA", ds2.col("sum(BA_MALE)").plus(ds2.col("sum(BA_FEMALE)")))
                .withColumn("IA", ds2.col("sum(IA_MALE)").plus(ds2.col("sum(IA_FEMALE)")))
                .withColumn("AA", ds2.col("sum(AA_MALE)").plus(ds2.col("sum(AA_FEMALE)")))
                .withColumn("NA", ds2.col("sum(NA_MALE)").plus(ds2.col("sum(NA_FEMALE)")))
                .withColumn("TOM", ds2.col("sum(TOM_MALE)").plus(ds2.col("sum(TOM_FEMALE)")));

        Dataset ds4 = ds3.withColumn("TOTAL", ds3.col("WA").plus(ds3.col("BA")).plus(ds3.col("IA")).plus(ds3.col("AA"))
                .plus(ds3.col("NA")).plus(ds3.col("TOM")));

        Dataset ds5 = ds4.withColumn("DIFFWA", ds4.col("TOTAL").minus(ds4.col("WA")).multiply(ds4.col("WA")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")))
                .withColumn("DIFFBA", ds4.col("TOTAL").minus(ds4.col("BA")).multiply(ds4.col("BA")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")))
                .withColumn("DIFFIA", ds4.col("TOTAL").minus(ds4.col("IA")).multiply(ds4.col("IA")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")))
                .withColumn("DIFFAA", ds4.col("TOTAL").minus(ds4.col("AA")).multiply(ds4.col("AA")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")))
                .withColumn("DIFFNA", ds4.col("TOTAL").minus(ds4.col("NA")).multiply(ds4.col("NA")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")))
                .withColumn("DIFFTOM", ds4.col("TOTAL").minus(ds4.col("TOM")).multiply(ds4.col("TOM")).divide(ds4.col("TOTAL")).divide(ds4.col("TOTAL")));
        //ds5.show();
        Dataset ds6 = ds5.withColumn("Index", ds5.col("DIFFWA").plus(ds5.col("DIFFBA")).plus(ds5.col("DIFFIA")).plus(ds5.col("DIFFAA")).plus(ds5.col("DIFFNA")).plus(ds5.col("DIFFTOM")))
                .sort(ds5.col("STNAME"),ds5.col("CTYNAME"));

        Dataset ds7 = ds6.withColumnRenamed("sum(YEAR)", "YEAR").withColumnRenamed("sum(AGEGRP)", "AGEGRP")
                .withColumnRenamed("sum(WA_MALE)", "WA_MALE").withColumnRenamed("sum(WA_FEMALE)", "WA_FEMALE")
                .withColumnRenamed("sum(BA_MALE)", "BA_MALE").withColumnRenamed("sum(BA_FEMALE)", "BA_FEMALE")
                .withColumnRenamed("sum(IA_MALE)", "IA_MALE").withColumnRenamed("sum(IA_FEMALE)", "IA_FEMALE")
                .withColumnRenamed("sum(AA_MALE)", "AA_MALE").withColumnRenamed("sum(AA_FEMALE)", "AA_FEMALE")
                .withColumnRenamed("sum(NA_MALE)", "NA_MALE").withColumnRenamed("sum(NA_FEMALE)", "NA_FEMALE")
                .withColumnRenamed("sum(TOM_MALE)", "TOM_MALE").withColumnRenamed("sum(TOM_FEMALE)", "TOM_FEMALE");
        ds7.show();

        Dataset fds = ds7.select("STNAME","CTYNAME","Index");
        JavaRDD rdd1 = fds.javaRDD().repartition(1);
        //deleteDirectory(new File(OutputDirectory + "_Index"));
        deleteDirectory(new File(OutputDirectory + "_Index"));
        rdd1.saveAsTextFile(OutputDirectory+"_Index");

    }


    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "D:\\Courses\\parallel computing\\hadoop-2.6.0");
        // Create a SparkConf that loads defaults from system properties and the classpath
        SparkConf sparkConf = new SparkConf();
        sparkConf.set("spark.master", "local[8]");
        //Provides the Spark driver application a name for easy identification in the Spark or Yarn UI
        sparkConf.setAppName("Word Count");

        // Creating a session to Spark. The session allows the creation of the
        // various data abstractions such as RDDs, DataFrame, and more.
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

        // Creating spark context which allows the communication with worker nodes
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        try {
            calculateIndex(spark);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stop existing spark context
        jsc.close();

        // Stop existing spark session
        spark.close();
    }
}
