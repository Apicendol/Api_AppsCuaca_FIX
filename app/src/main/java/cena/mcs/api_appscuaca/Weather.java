package cena.mcs.api_appscuaca;

public class Weather {

//    private String tanggal;
//String tanggal, kodecuaca, cuaca;

//    private int kodecuaca;

//    public Weather(String tanggal, int kodecuaca) {
//    public Weather(String tanggal, String kodecuaca, String cuaca) {
//        this.tanggal = tanggal;
//        this.kodecuaca = kodecuaca;
//        this.cuaca = cuaca;
//    }

//    public String getTanggal() {
//        return tanggal;
//    }
//    public int getKodecuaca() {
//        return kodecuaca;
//    }

//    String day, code, weather;
    String day;
    int code, icon;

//    public Weather(String day, String weather, String code){
    public Weather(String day, int code, int icon){
        this.day = day;
        this.code = code;
        this.icon = icon;
    }

    public String getDay() {
        return day;
    }

    public int getCode() {
        return code;
    }

    public int getIcon() {
        return icon;
    }
}
