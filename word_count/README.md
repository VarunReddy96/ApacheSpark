### Download the dataset
* Download the ["Amazon fine food reviews"](https://www.kaggle.com/snap/amazon-fine-food-reviews/downloads/amazon-fine-food-reviews.zip/2) dataset
* Extract a file "Reviews.csv" into a folder called "dataset" and rename it to "amazon-reviews.csv"

You should have the following
```
dataset/amazon-reviews.csv
``` 

### Run this example in this folder
```
mvn package
java -cp target/word_count-1.0-SNAPSHOT.jar edu.rit.cs.WordCount_Seq
```

Or
```
java -cp target/word_count-1.0-SNAPSHOT.jar edu.rit.cs.WordCount_Seq_Improved
```


### Word Count example in Spark
```
mvn compile; mvn package
mvn exec:java -Dexec.mainClass=edu.rit.cs.WordCount_Spark
```