import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        String fileName2 = "file2.txt";
        String fileName3 = "file3.txt";

        ArrayList<Point> points = new ArrayList();
        ArrayList<Line> lines = new ArrayList();

//        try{
//            if(points.size() <= 1){
//                throw new Exception("Недостаточно информации!");
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return;
//        }
        File.readFile(points);
        File.writeFile(points, fileName3, false);

        double totalLength = 0.0;
        double lenMax = 0.0;
        Line lineMax = null;

        for (int i = 0; i < points.size() - 1; i++) {
            lines.add(new Line(points.get(i), points.get(i + 1)));

            double len = lines.get(i).length();

            if (len > lenMax) {
                lenMax = len;
                lineMax = lines.get(i);
            }
            totalLength += len;
        }
        String averageLen = "\nСредняя длина линии = " + totalLength / Line.getCount();
        String strLenMax = "\nОтрезок максимальной длины: \n" + lineMax.toString();

        File.writeFile(lines, fileName2, false, averageLen, strLenMax);
        File.writeFile(lines, fileName3, true, averageLen, strLenMax);
    }
}
