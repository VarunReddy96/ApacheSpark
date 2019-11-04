package edu.rit.cs;

//Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,HelpfulnessDenominator,Score,Time,Summary,Text
public class AmazonFineFoodReview {

    private int Id;
    private String ProductId;
    private String UserID;
    private String ProfileName;
    private int HelpfulnessNumerator;
    private int HelpfulnessDenominator;
    private int Score;
    private int Time;
    private String Summary;
    private String Text;

    public AmazonFineFoodReview(String reviewLine) {
        String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                        ",                         "+ // match a comma
                        "(?=                       "+ // start positive look ahead
                        "  (?:                     "+ //   start non-capturing group 1
                        "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                        "    %s                    "+ //     match 'quotedString'
                        "  )*                      "+ //   end group 1 and repeat it zero or more times
                        "  %s*                     "+ //   match 'otherThanQuote'
                        "  $                       "+ // match the end of the string
                        ")                         ", // stop positive look ahead
                otherThanQuote, quotedString, otherThanQuote);

        String [] data = reviewLine.split(regex, -1);

        try {
            this.Id = Integer.valueOf(data[0]);
            this.ProductId = data[1];
            this.UserID = data[2];
            this.ProfileName = data[3];
            this.HelpfulnessNumerator = Integer.valueOf(data[4]);
            this.HelpfulnessDenominator = Integer.valueOf(data[5]);
            this.Score = Integer.valueOf(data[6]);
            this.Time = Integer.valueOf(data[7]);
            this.Summary = data[8];
            this.Text = data[9];
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String ProfileName) {
        this.ProfileName = ProfileName;
    }

    public int getHelpfulnessNumerator() {
        return HelpfulnessNumerator;
    }

    public void setHelpfulnessNumerator(int HelpfulnessNumerator) {
        this.HelpfulnessNumerator = HelpfulnessNumerator;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }
}
