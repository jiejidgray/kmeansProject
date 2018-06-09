package clustering;

public class KmeansData {
	
 private float id;
 private float date;
 private float sum;
 private int nCluster;
 
 public KmeansData(float id, float date, float sum,int nCluster){
	 this.id = id;
	 this.date = date;
	 this.sum = sum;
	 this.nCluster = nCluster;
 }
 public float getId() {
	return id;
}

public void setId(float id) {
	this.id = id;
}

public float getDate() {
	return date;
}

public void setDate(float date) {
	this.date = date;
}

public float getSum() {
	return sum;
}

public void setSum(float sum) {
	this.sum = sum;
}

@Override
public String toString() {
    return  id + "" + date + "" + sum +"";
	
}
public float getnCluster() {
	return nCluster;
}
public void setnCluster(int nCluster) {
	this.nCluster = nCluster;
}
}