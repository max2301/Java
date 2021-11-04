import java.io.*;
import java.util.ArrayList;

public class File {
    public static void readFile(ArrayList<Point> points){
        try {
            BufferedReader br = new BufferedReader(new FileReader("file1.txt"));
            String str;

            while ((str = br.readLine()) != null) {
                if (str.length() == 0) {
                    continue;
                }
                String[] coords = str.split(", ");//"\\s*(\\s|,|:)\\s*"
                points.add(new Point(Double.parseDouble(coords[0]), Double.parseDouble(coords[1])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeFile(ArrayList<Line> lines,String fileName, Boolean app, String averageLen, String lenMax){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,app));

            bw.write("Преобразованная информация:\n");
            for(Line line : lines){
                bw.write(line.toString());
            }
            bw.write(averageLen);
            bw.write(lenMax);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(ArrayList<Point> points,String fileName, Boolean app ){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,app));

            bw.write("Исходная информация:\n");
            for(Point point : points){
                bw.write(point.toString()+"\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
