package ma.enset.n7flix.recommendation_algorithm;

public class CosineSimilarity {
    double result;
    public CosineSimilarity(byte[] a,byte[] b){
        double top=0,bottomLeft=0,bottomRight=0;
        for (int i = 0; i < a.length; i++) {
            top+=a[i]*b[i];
            bottomLeft += a[i]*a[i];
            bottomRight += b[i]*b[i];
        }
        result=top/(Math.sqrt(bottomLeft)*Math.sqrt(bottomRight));
    }

    public double getResult() {
        return this.result;
    }
}
