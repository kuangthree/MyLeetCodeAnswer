import solutions.DailyTemperatures;
public class Main {
    public static void main(String[] args){
        int[] a = new DailyTemperatures().solve(new int[]{73,74,75,71,69,72,76,73});
        for(int k:a){
            System.out.print(k+", ");
        }
    }

}
