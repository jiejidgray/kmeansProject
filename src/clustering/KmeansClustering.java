package clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KmeansClustering {

	 private int k;// combien de cluster    
	    private int m;// nb d'int¨¦ration
	    private int dataSetLength;
	    private ArrayList<KmeansData> dataSet;//tableau de data
	    private ArrayList<ArrayList<Float>> hopelist;
	    public ArrayList<KmeansData> getCenter() {
			return center;
		}

		public ArrayList<ArrayList<Float>> getHopelist() {
			return hopelist;
		}

		public void setHopelist(ArrayList<ArrayList<Float>> hopelist) {
			this.hopelist = hopelist;
		}

		public void setCenter(ArrayList<KmeansData> center) {
			this.center = center;
		}

		private ArrayList<KmeansData> center;// tableau de centres
	    private ArrayList<ArrayList<KmeansData>> cluster; // tableau de cluster    
	    private ArrayList<Float> jc;// somme des carr¨¦s d'erreur   
	    private Random random;    
	     
	    public void setDataSet(ArrayList<KmeansData> dataSet) {    
	        this.dataSet = dataSet;    
	    }    
	  
	    public ArrayList<ArrayList<KmeansData>> getCluster() {    
	        return cluster;    
	    }    
	     
	    public KmeansClustering (int k) {    
	        if (k <= 0) {    
	            k = 1;    
	        }    
	        this.k = k;    
	    }    
	    
	    private void init() {   
	        m = 0;    
	        random = new Random();    
	        if (dataSet == null || dataSet.size() == 0) {     
	            System.out.println("pas de data");  
	        } else{  
	            dataSetLength = dataSet.size();    
	            if (k > dataSetLength) {    
	                k = dataSetLength;    
	            }    
	            center = initCenters();    
	            cluster = initCluster();    
	            jc = new ArrayList<Float>();    
	            }  
	    }    
	    
	    private ArrayList<KmeansData> initCenters() {    
	        ArrayList<KmeansData> center = new ArrayList<KmeansData>();    
	        int[] randoms = new int[k];    
	        boolean flag;    
	        int temp = random.nextInt(dataSetLength);
	        
	        randoms[0] = temp;    
	        for (int i = 1; i < k; i++) {    
	            flag = true;    
	            while (flag) {    
	                temp = random.nextInt(dataSetLength);    
	                int j = 0;    
	                while (j < i) {    
	                    if (temp == randoms[j]) {    
	                        break;    
	                    }    
	                    j++;    
	                }    
	                if (j == i) {    
	                    flag = false;    
	                }    
	            }    
	            randoms[i] = temp;  
	            
	        }     
	        for (int i = 0; i < k; i++) {    
	            center.add(dataSet.get(randoms[i]));// g¨¦n¨¦rer le talbleau des centres
	        }    
	        return center;    
	    }    
	    
	   
	    private ArrayList<ArrayList<KmeansData>> initCluster() {    
	        ArrayList<ArrayList<KmeansData>> cluster = new ArrayList<ArrayList<KmeansData>>();    
	        for (int i = 0; i < k; i++) {    
	            cluster.add(new ArrayList<KmeansData>());    
	        }    
	    
	        return cluster;    
	    }    
	    
	  
	    private float distance(KmeansData element, KmeansData center) {    
	        //calculer la distance entre deux pointes
	        float distance = 0.0f;    
	        float x = element.getDate() - center.getDate();    
	        float y = element.getSum() - center.getSum();    
	        float z = x * x + y * y;    
	        distance = (float) Math.sqrt(z);    
	        
	        return distance;    
	    }    
	    
	    
	    private int minDistance(float[] distance) {    
	         //obtenir la position de min distance
	        float minDistance = distance[0];    
	        int minLocation = 0;    
	        for (int i = 1; i < distance.length; i++) {    
	            if (distance[i] < minDistance) {    
	                minDistance = distance[i];    
	                minLocation = i;    
	            } else if (distance[i] == minDistance) // si ¨¦gale, retourner une position al¨¦atoirement
	            {    
	                if (random.nextInt(10) < 5) {    
	                    minLocation = i;    
	                }    
	            }    
	        }    
	    
	        return minLocation;    
	    }    
	    
	   
	    private void clusterSet() {    
	        //placer l'¨¦l¨¦ment dans le cluser ou la distance entre le centre est plus courte
	        float[] distance = new float[k];    
	        for (int i = 0; i < dataSetLength; i++) {    
	            for (int j = 0; j < k; j++) {    
	                distance[j] = distance(dataSet.get(i), center.get(j));    
	            }    
	            int minLocation = minDistance(distance);    
	            cluster.get(minLocation).add(dataSet.get(i));    
	    
	        }    
	    }    
	    
	  
	    private float errorSquare(KmeansData element, KmeansData center) {  
	        //alculer la carr¨¦ d'erreur entre deux points
	        float x = element.getDate() - center.getDate();    
	        float y = element.getSum() - center.getSum();    
	    
	        float errSquare = x * x + y * y;    
	    
	        return errSquare;    
	    }    
	    
	    
	    private void countRule() {    
	        float jcF = 0;    
	        for (int i = 0; i < cluster.size(); i++) {    
	            for (int j = 0; j < cluster.get(i).size(); j++) {    
	                jcF += errorSquare(cluster.get(i).get(j), center.get(i));    
	    
	            }    
	        }    
	        jc.add(jcF);    
	    }    
	    private void setNewCenter() {   
	        for (int i = 0; i < k; i++) {    
	            int n = cluster.get(i).size();    
	            if (n != 0) {    
	                KmeansData newCenter = new KmeansData(0,0,0,0);    
	                for (int j = 0; j < n; j++) {
	                	newCenter.setDate(newCenter.getDate()+cluster.get(i).get(j).getDate());
	                    newCenter.setSum(newCenter.getSum()+cluster.get(i).get(j).getSum());    
	                }    
	                // calculer une moyenne  
	                newCenter.setDate(newCenter.getDate() / n);    
	                newCenter.setSum(newCenter.getSum() / n);     
	                center.set(i, newCenter);    
	            }    
	        }    
	    }    
	      
	    public List<String[]> printDataArray(ArrayList<KmeansData> dataArray, int nCluster) {
	    
	    	
	    	List<String[]> ExportList = new ArrayList<String[]>();
	        //afficher le r¨¦sultat 
	        for (int i = 0; i < dataArray.size(); i++) {    
	        	dataArray.get(i).setnCluster(nCluster+1);
	         //   System.out.println("print:("+dataArray.get(i).getId()+"," + dataArray.get(i).getDate() + "," + dataArray.get(i).getSum()+","+dataArray.get(i).getnCluster()+")");
	            String strid = String.valueOf(dataArray.get(i).getId());
	            String strx = String.valueOf(dataArray.get(i).getDate());
	            String stry = String.valueOf(dataArray.get(i).getSum());
	            String strn = String.valueOf(dataArray.get(i).getnCluster());
	            ExportList.add(new String[] { strid,strx , stry, strn});
	        }    
	        //System.out.println("===================================");    
	        return ExportList;
	    }     
	     
	    void kmeans() {    
	        init();   
	        while (true) {    
	             clusterSet();    
	             countRule();     
	          
	             if (m != 0) {    
	                 if (jc.get(m) - jc.get(m - 1) == 0) {    
	                     break;    
	                 }    
	             }    
	     
	             setNewCenter();     
	             m++;    
	             cluster.clear();    
	             cluster = initCluster();    
	         }
	         
	         hopelist = new ArrayList<ArrayList<Float>>();
	         ArrayList<ArrayList<Float>> hopelist2 = new ArrayList<ArrayList<Float>>();
	         for(int i=0;i<k;i++){
	          float x = center.get(i).getDate();
	          float y = center.get(i).getSum();
	          float sum = x*x+y*y;
	          ArrayList<Float> temlist = new ArrayList<Float>();
	          temlist.add(0, sum);
	          temlist.add(1, (float)i);
	          hopelist2.add(i, temlist); 
	         }
	         int times = 0;
	         while(times<k){
	          float min = hopelist2.get(0).get(0);
	          int minIndex = 0;
	          for(int i=0; i<k-times; i++){
	           float difference = hopelist2.get(i).get(0) - min;
	           if(difference > 0){
	            min = hopelist2.get(i).get(0);
	            minIndex = i;
	           }
	          }
	          
	          hopelist.add(times, hopelist2.get(minIndex));
	          hopelist2.remove(minIndex);
	          
	          times++;
	          
	         }
	        
	        // r¨¦p¨¦ter justqu'¨¤ l'erreur ne change pas
	       /* while (true) {    
	            clusterSet();    
	            countRule();     
	         
	            if (m != 0) {    
	                if (jc.get(m) - jc.get(m - 1) == 0) {    
	                    break;    
	                }    
	            }    
	    
	            setNewCenter();     
	            m++;    
	            cluster.clear();    
	            cluster = initCluster();    
	        }
	        for(int j=0;j<center.size();j++) {
	        	System.out.print(center.get(j).toString()+"/n");
	        	System.out.println("===================================");    
	        }*/
	        	
	    }

		public int getM() {
			return m;
		}

		public void setM(int m) {
			this.m = m;
		}
	
}
