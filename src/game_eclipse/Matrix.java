package game_eclipse;

public class Matrix {

public static void printMatrix(int[][] A) {  //i = zeilen, j = spalten   // 0 nichts 1 hindernis 2 feind 3 hero 4 türe runter(portal) 5 türe hoch(hero spawn) Startpunkt

	
StdDraw.setCanvasSize(800,800);	
StdDraw.clear();	

	        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
            	
                    if(A[i][j] == 0){                    
                    }
                    else if(A[i][j] == 1){
                    StdDraw.rectangle(0.09*j, 0.09*i, 0.045, 0.045);
                    }
                    else if(A[i][j] == 2){
                    StdDraw.circle(0.09*j, 0.09*i, 0.01);
                    }
                    else if(A[i][j] == 3){
                    StdDraw.filledCircle(0.09*j, 0.09*i, 0.01);
                    }
                    else if(A[i][j] == 4){
                    StdDraw.rectangle(0.09*j, 0.09*i, 0.0225, 0.045);
                    }
                    else if(A[i][j] == 5){
                    StdDraw.filledRectangle(0.09*j, 0.09*i, 0.0225, 0.045);  
                    }
            }
                  
        }
        
    }
	
	}