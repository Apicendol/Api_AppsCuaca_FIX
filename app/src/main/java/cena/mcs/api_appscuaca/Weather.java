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

    String day, code, weather;

    public Weather(String day, String weather, String code){
        this.day = day;
        this.weather = weather;
        this.code = code;
    }
}
