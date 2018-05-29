package clustering;

public class KmeansData {
	
 private double id;
 private double date;
 private double sum;
 
 public KmeansData(double id, double date, double sum){
	 this.id = id;
	 this.date = date;
	 this.sum = sum;
 }
 public double getId() {
	return id;
}

public void setId(double id) {
	this.id = id;
}

public double getDate() {
	return date;
}

public void setDate(double date) {
	this.date = date;
}

public double getSum() {
	return sum;
}

public void setSum(double sum) {
	this.sum = sum;
}


	
}
