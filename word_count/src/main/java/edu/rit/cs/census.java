package edu.rit.cs;

public class census {
    private String STNAME,CTYNAME;
    private double YEAR,AGEGRP,WA_MALE,WA_FEMALE,BA_MALE,BA_FEMALE,IA_MALE,IA_FEMALE,AA_MALE,AA_FEMALE,NA_MALE,NA_FEMALE,TOM_MALE,TOM_FEMALE;
    private double DIFFWA,DIFFBA,DIFFIA,DIFFAA,DIFFNA,DIFFTOM,WA,BA,IA,AA,NA,TOM,TOTAL;

    public String getSTNAME() {
        return STNAME;
    }

    public void setSTNAME(String STNAME) {
        this.STNAME = STNAME;
    }

    public String getCTYNAME() {
        return CTYNAME;
    }

    public void setCTYNAME(String CTYNAME) {
        this.CTYNAME = CTYNAME;
    }

    public double getYEAR() {
        return YEAR;
    }

    public void setYEAR(double YEAR) {
        this.YEAR = YEAR;
    }

    public double getAGEGRP() {
        return AGEGRP;
    }

    public void setAGEGRP(double AGEGRP) {
        this.AGEGRP = AGEGRP;
    }

    public double getWA_MALE() {
        return WA_MALE;
    }

    public void setWA_MALE(double WA_MALE) {
        this.WA_MALE = WA_MALE;
    }

    public double getWA_FEMALE() {
        return WA_FEMALE;
    }

    public void setWA_FEMALE(double WA_FEMALE) {
        this.WA_FEMALE = WA_FEMALE;
    }

    public double getBA_MALE() {
        return BA_MALE;
    }

    public void setBA_MALE(double BA_MALE) {
        this.BA_MALE = BA_MALE;
    }

    public double getBA_FEMALE() {
        return BA_FEMALE;
    }

    public void setBA_FEMALE(double BA_FEMALE) {
        this.BA_FEMALE = BA_FEMALE;
    }

    public double getIA_MALE() {
        return IA_MALE;
    }

    public void setIA_MALE(double IA_MALE) {
        this.IA_MALE = IA_MALE;
    }

    public double getIA_FEMALE() {
        return IA_FEMALE;
    }

    public void setIA_FEMALE(double IA_FEMALE) {
        this.IA_FEMALE = IA_FEMALE;
    }

    public double getAA_MALE() {
        return AA_MALE;
    }

    public void setAA_MALE(double AA_MALE) {
        this.AA_MALE = AA_MALE;
    }

    public double getAA_FEMALE() {
        return AA_FEMALE;
    }

    public void setAA_FEMALE(double AA_FEMALE) {
        this.AA_FEMALE = AA_FEMALE;
    }

    public double getNA_MALE() {
        return NA_MALE;
    }

    public void setNA_MALE(double NA_MALE) {
        this.NA_MALE = NA_MALE;
    }

    public double getNA_FEMALE() {
        return NA_FEMALE;
    }

    public void setNA_FEMALE(double NA_FEMALE) {
        this.NA_FEMALE = NA_FEMALE;
    }

    public double getTOM_MALE() {
        return TOM_MALE;
    }

    public void setTOM_MALE(double TOM_MALE) {
        this.TOM_MALE = TOM_MALE;
    }

    public double getTOM_FEMALE() {
        return TOM_FEMALE;
    }

    public void setTOM_FEMALE(double TOM_FEMALE) {
        this.TOM_FEMALE = TOM_FEMALE;
    }

    public double getDIFFWA() {
        return DIFFWA;
    }

    public void setDIFFWA(double DIFFWA) {
        this.DIFFWA = DIFFWA;
    }

    public double getDIFFBA() {
        return DIFFBA;
    }

    public void setDIFFBA(double DIFFBA) {
        this.DIFFBA = DIFFBA;
    }

    public double getDIFFIA() {
        return DIFFIA;
    }

    public void setDIFFIA(double DIFFIA) {
        this.DIFFIA = DIFFIA;
    }

    public double getDIFFAA() {
        return DIFFAA;
    }

    public void setDIFFAA(double DIFFAA) {
        this.DIFFAA = DIFFAA;
    }

    public double getDIFFNA() {
        return DIFFNA;
    }

    public void setDIFFNA(double DIFFNA) {
        this.DIFFNA = DIFFNA;
    }

    public double getDIFFTOM() {
        return DIFFTOM;
    }

    public void setDIFFTOM(double DIFFTOM) {
        this.DIFFTOM = DIFFTOM;
    }

    public double getWA() {
        return WA;
    }

    public void setWA(double WA) {
        this.WA = WA;
    }

    public double getBA() {
        return BA;
    }

    public void setBA(double BA) {
        this.BA = BA;
    }

    public double getIA() {
        return IA;
    }

    public void setIA(double IA) {
        this.IA = IA;
    }

    public double getAA() {
        return AA;
    }

    public void setAA(double AA) {
        this.AA = AA;
    }

    public double getNA() {
        return NA;
    }

    public void setNA(double NA) {
        this.NA = NA;
    }

    public double getTOM() {
        return TOM;
    }

    public void setTOM(double TOM) {
        this.TOM = TOM;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    //    public census(String reviewLine){
//        String regex = String.format(",");
//        String [] data = reviewLine.split(regex, -1);
//        try {
//            this.STNAME = data[3];
//            this.CTYNAME = data[4];
//            this.YEAR = Integer.valueOf(data[5]);
//            this.AGEGRP = Integer.valueOf(data[6]);
//            this.WA_MALE = Integer.valueOf(data[10]);
//            this.WA_FEMALE = Integer.valueOf(data[11]);
//            this.BA_MALE = Integer.valueOf(data[12]);
//            this.BA_FEMALE = Integer.valueOf(data[13]);
    //        this.IA_MALE = Integer.valueOf(data[14]);
//            this.IA_FEMALE = Integer.valueOf(data[15]);
//            this.AA_MALE = Integer.valueOf(data[16]);
//            this.AA_FEMALE = Integer.valueOf(data[17]);
//            this.NA_MALE = Integer.valueOf(data[18]);
//            this.BA_FEMALE = Integer.valueOf(data[19]);
//            this.TOM_MALE = Integer.valueOf(data[20]);
//            this.TOM_FEMALE = Integer.valueOf(data[21]);
//        }catch(Exception e){
    //            System.err.println(e.toString());
//        }
//    }



}



