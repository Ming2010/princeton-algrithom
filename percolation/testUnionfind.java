import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class testUnionfind {
    public static void main(String[] args){
        QuickFindUF uf = new QuickFindUF(5);
        uf.union(1, 2);
        uf.union(1, 4);
        StdOut.println(uf.find(2));
        StdOut.println(uf.find(1));
        StdOut.println(uf.find(4));
    }
}
