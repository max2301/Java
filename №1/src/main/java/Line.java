public class Line {
    private Point start;
    private Point end;
    private static int count;

    public Line(final Point start, final Point end) {
        count++;
        this.start = new Point(start);
        this.end = new Point(end);
    }

    public static int getCount() {
        return count;
    }

    public double length() {
        return start.distance(end);
    }

    public String toString() {
        return "Линия " + "(" + start + "):(" + end + ")" + " длина линии " + this.length() + "\n";
    }

}
