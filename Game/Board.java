// Name: Luke Power
// Name: Venkata Vadrevu

import java.util.*;
import java.util.HashMap;
import java.util.Map;



class Board{

	public String[][] B;
	public int count;


	public HashSet<String> whiteAlive;
	public HashSet<String> blackAlive;
	public HashMap<String, Vector<Integer>> M;
	public HashMap<String, Boolean> moved;

	//  0: unmoved
	//  1: moved by onestep
	//  2: moved by 2 steps atleast one step before
	//  3: moved by 2 step one step ago
	// -2: promoted
	// -1: dead
	//  4: moved and 2 steps

	public int[] whitePawns;
	public int[] blackPawns;

	public void printWhiteAlive()
	{
		System.out.println("WhiteAlive");
		System.out.println("------------------------------");
		Iterator<String> itr = whiteAlive.iterator();

		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
		System.out.println("------------------------------");

	}

	public void printBlackAlive()
	{
		System.out.println("BlackAlive");
		System.out.println("------------------------------");
		Iterator<String> itr = blackAlive.iterator();

		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
		System.out.println("------------------------------");
	}

	public void printM()
	{
		System.out.println("M");
		System.out.println("------------------------------");
		Iterator itr = M.entrySet().iterator();

		while(itr.hasNext())
		{
			Map.Entry mapElement = (Map.Entry)itr.next();

			Vector<Integer> cor =(Vector<Integer>) mapElement.getValue();
			System.out.println(mapElement.getKey() + " --> " + (int)cor.get(0) + " " + (int)cor.get(1));
		}
		System.out.println("------------------------------");
	}

	public void printMoved()
	{
		System.out.println("Moved");
		System.out.println("------------------------------");
		Iterator itr = moved.entrySet().iterator();

		while(itr.hasNext())
		{
			Map.Entry mapElement = (Map.Entry)itr.next();

			boolean cor = (boolean)mapElement.getValue();
			System.out.println(mapElement.getKey() + " --> " + cor);
		}
		System.out.println("------------------------------");

	}

	public Board()
	{
		B = new String[8][8];


		count = 0;
		whiteAlive = new HashSet<String>();
		blackAlive = new HashSet<String>();

		whitePawns = new int[8];
		blackPawns = new int[whitePawns.length];

		for(int i = 0; i < whitePawns.length; i++)
		{
			whitePawns[i] = 0;
			blackPawns[i] = 0;
		}

		M = new HashMap<String, Vector<Integer>>();
		moved = new HashMap<String, Boolean>();
		// white is at the top
		String p[] = new String[]{"w", "b"};
		// intialize the pawns
		for(int j = 0; j < p.length; j++)
		{
			int r = (j == 0)? 1:6;
			for(int i = 0; i < 8; i++)
			{
				B[r][i] = p[j]+"p" + Integer.toString(i);
			}
			int r_ = (r == 1)? 0:7;
			B[r_][0] = p[j]+"r0";
			B[r_][1] = p[j]+"n0";
			B[r_][2] = p[j]+"b0";
			B[r_][3] = p[j]+"q";
			B[r_][4] = p[j]+"k";
			B[r_][5] = p[j]+"b1";
			B[r_][6] = p[j]+"n1";
			B[r_][7] = p[j]+"r1";
		}
		for(int i = 2; i < 6; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				B[i][j] = "";
			}
		}
		//intialize the map
		for(int i = 0; i < B.length; i++)
		{
			for(int j = 0; j < B[0].length; j++)
			{
				if(!B[i][j].equals(""))
				{
					Vector<Integer> v = new Vector<Integer>();
					v.add(i); v.add(j);
					M.put(B[i][j], v);
					moved.put(B[i][j], false);

					if(B[i][j].charAt(0) - 'w' == 0)
					{
						whiteAlive.add(B[i][j]);
					}
					else
					{
						blackAlive.add(B[i][j]);
					}
				}
			}
		}
	}


	public void display()
	{
		/**Prints the board to the screen*/
		System.out.println("    a      b      c    d    e    f    g      h\n");

		for(int i = 0; i < 8; i++)
		{
			System.out.printf((i+1) + " ");
			for(int j = 0; j < 8; j++)
			{

				String t = (B[i][j].equals(""))?"   ":B[i][j];
				if(!B[i][j].equals("") && (B[i][j].charAt(1) - 'q' == 0 ||B[i][j].charAt(1) - 'k' == 0))
				{
					if(j != 7){
						System.out.printf("| %s  ", t);
					}
					else
					{
						System.out.println("| "+ t + "  |");
					}
				}
				else{
					if(j != 7){
						System.out.printf("| %s ", t);
					}
					else
					{
						System.out.println("| "+ t + " |");
					}
				}
			}
			System.out.println("  -----------------------------------------------");
		}
	}

// 	public void play()
// 	{
// 		int r, c;
// 		int r_, c_;
// 		Scanner input = new Scanner(System.in);
// 		boolean isPawnMove = false;
// 		count = 0;
// 		while(true)
// 		{
// 
// 			//System.out.println((checkMate(0)));
// 			//System.out.println(checkMate(1));
// 			isPawnMove = false;
// 			if(count%2 == 0)
// 			{
// 				//System.out.println("White to play");
// 			}
// 			else
// 			{
// 				//System.out.println("Black to play");
// 			}

// 			char player = (count%2 == 0)?'w':'b';

// 			boolean successfulMove = false;

// 			do{
// 				do{
// 					//System.out.println("row: ");
// 					r = input.nextInt();
// 					//System.out.println("col: ");
// 					c = input.nextInt();

// 					r -= 1;c -= 1;

// 				}while(!filled(count%2, r, c));

// 				do{
// 					//System.out.println("to row:");
// 					r_ = input.nextInt();
// 					//System.out.println("to col:");
// 					c_ = input.nextInt();

// 					r_ -= 1; c_ -= 1;

// 				}while(!(isValid(r_, c_) && !filled(count%2, r_, c_)));
// 				successfulMove = movePiece(r, c, r_, c_);
// 				if(successfulMove && B[r_][c_].charAt(1) - 'p' == 0)
// 				{
// 					isPawnMove = true;
// 				}
// 				//System.out.println(successfulMove);
// 			}while(!successfulMove);
// 	      if(isPawnMove && B[r_][c_].charAt(1)-'p'==0 && Math.abs(r - r_) == 1 && Math.abs(c-c_) == 1)
// 	      {
// 	        int sign = ((B[r_][c_].charAt(0)-'w')==0)?-1:1;
// 	        if(isValid(r_+sign ,c_) &&!empty(r_+sign,c_))
// 	        {
// 	          int index = Integer.parseInt(""+B[r_+sign][c_].charAt(2) );
// 	          if(count%2 == 0 && blackPawns[index]==3)
// 	          {
// 	            B[r_+sign][c_] = "";
// 	          }
// 	          else if(count%2 == 1 && whitePawns[index]==3)
// 	          {
// 	            B[r_+sign][c_] = "";
// 	          }
// 	        }
// 	      }
//       		// //System.out.println(isPawnMove);
//       		// //System.out.println(promotion(r_, c_));
// 			if(isPawnMove && promotion(r_,c_))
// 			{
// 				// return 2;
// 				//String p = (count%2 == 0)?"w":"b";
// 				//System.out.println("What do u want to promote to?");
// 				//System.out.println("q");
// 				//System.out.println("n");
// 				//System.out.println("r");
// 				//System.out.println("b");
// 				//String promotedTo = input.nextLine();
//        			String originalPiece = B[r_][c_];
//        			int ind = Integer.parseInt(""+B[r_][c_].charAt(2));


//        			M.remove(originalPiece);
//        			if(count%2 == 0){whitePawns[ind] = -2; }
//        			else{blackPawns[ind] = -2;}

//        			char promotedPiece = input.next().charAt(0);
// 				B[r_][c_] = p+(""+promotedPiece)+String.valueOf(count);

// 				moved.put(B[r_][c_], false);

// 			}



// 			if(count%2 == 0)
// 			{
// 				for(int i = 0; i < blackPawns.length; i++)
// 				{
// 				  String piece = "bp" + String.valueOf(i);

// 				  if(blackAlive.contains(piece) && blackPawns[i] == 3){blackPawns[i] = 2;}
// 				  else if(!blackAlive.contains(piece)){blackPawns[i] = -1;}

// 				}
// 			}
// 			else
// 			{
// 				for(int i = 0; i < whitePawns.length; i++)
// 				{
// 				  String piece = "wp" + String.valueOf(i);
// 				  if(whiteAlive.contains(piece) && whitePawns[i] == 3){whitePawns[i] = 2;}
// 				  else if(!whiteAlive.contains(piece)){whitePawns[i] = -1;}
// 				}
// 			}

// 			M = new HashMap<String, Vector<Integer>>();
// 			whiteAlive = new HashSet<String>();
// 			blackAlive = new HashSet<String>();
// 			for(int i = 0; i < B.length; i++)
// 			{
// 				for(int j = 0; j < B[0].length; j++)
// 				{
// 					if(!B[i][j].equals(""))
// 					{
// 						Vector<Integer> v = new Vector<Integer>();
// 						v.add(i); v.add(j);
// 						M.put(B[i][j], v);
// 						if(B[i][j].charAt(0) - 'w' == 0)
// 						{
// 							whiteAlive.add(B[i][j]);
// 						}
// 						else
// 						{
// 							blackAlive.add(B[i][j]);
// 						}
// 					}
// 				}
// 			}

// 			display();
// //			Iterator itr = M.entrySet().iterator();
// //			while(itr.hasNext())
// //			{
// //				Map.Entry me = (Map.Entry)itr.next();
// //
// //				Vector<Integer> cordinates = (Vector<Integer>)me.getValue();
// //
// //
// //				Vector<Vector<Integer>> trajectory = getTrajectory((String)me.getKey());
// //				//System.out.println((String)me.getKey()+" "+(int)cordinates.get(0) + " " + (int)cordinates.get(1));
// //
// //				Iterator<Vector<Integer>> I = trajectory.iterator();
// //				while(I.hasNext())
// //				{
// //					Vector<Integer> pos = I.next();
// //					//System.out.println((int)pos.get(0)+" "+(int)pos.get(1));
// //				}
// //			}

// 			// for(int i = 0; i < 8; i++)
// 			// {
// 			// 	//System.out.println(whitePawns[i]);
// 			// }
// 			// for(int i = 0; i < 8; i++)
// 			// {
// 			// 	//System.out.println(blackPawns[i]);
// 			// }

// 			count++;
// 		}
// 	}


	// -2 if check mate
	//  0 if normal
	//  1 if promotion
	// -1 if move not valid
	public int playMove(int r, int c, int r_, int c_, int count)
	{
		
		this.count = count;

		if(empty(r, c)) {return -1;}
		else
		{
			char p = (count%2 == 0)? 'w':'b';
			if(B[r][c].charAt(0) - p != 0) {return -1;}
		}
		boolean isPawnMove = false;

		if((checkMate(0)||checkMate(1)))
		{
			return -2;
		}
		else{
			isPawnMove = false;

			char player = (count%2 == 0)?'w':'b';

			boolean successfulMove = false;

			successfulMove = movePiece(r, c, r_, c_);

			if(successfulMove && B[r_][c_].equals("") == false && B[r_][c_].charAt(1) - 'p' == 0)
			{
				isPawnMove = true;
			}
			if(!successfulMove)
			{
				return -1;
			}
		}

		if(isPawnMove && B[r_][c_].charAt(1)-'p'==0 && Math.abs(r - r_) == 1 && Math.abs(c-c_) == 1)
		{
		  int sign = ((B[r_][c_].charAt(0)-'w')==0)?-1:1;
			if(isValid(r_+sign ,c_) &&!empty(r_+sign,c_))
			{
				  int index = Integer.parseInt(""+B[r_+sign][c_].charAt(2) );

				  if(B[r_ + sign][c_].charAt(1) - 'p' == 0)
				  {
					  if(count%2 == 0 && blackPawns[index]==3)
					  {
					    B[r_+sign][c_] = "";
					  }
					  else if(count%2 == 1 && whitePawns[index]==3)
					  {
					    B[r_+sign][c_] = "";
					  }
					}
			}
		}



		if(count%2 == 0)
		{
			for(int i = 0; i < blackPawns.length; i++)
			{
			  String piece = "bp" + String.valueOf(i);

			  if(blackAlive.contains(piece) && blackPawns[i] == 3){blackPawns[i] = 2;}
			  else if(!blackAlive.contains(piece)){blackPawns[i] = -1;}

			}
		}

		else
		{
			for(int i = 0; i < whitePawns.length; i++)
			{
			  String piece = "wp" + String.valueOf(i);
			  if(whiteAlive.contains(piece) && whitePawns[i] == 3){whitePawns[i] = 2;}
			  else if(!whiteAlive.contains(piece)){whitePawns[i] = -1;}
			}
		}

		
		this.M = new HashMap<String, Vector<Integer>>();
		whiteAlive = new HashSet<String>();
		blackAlive = new HashSet<String>();
		for(int i = 0; i < B.length; i++)
		{
			for(int j = 0; j < B[0].length; j++)
			{
				if(!B[i][j].equals(""))
				{
					Vector<Integer> v = new Vector<Integer>();
					v.add(i); v.add(j);
					this.M.put(B[i][j], v);
					if(B[i][j].charAt(0) - 'w' == 0)
					{
						whiteAlive.add(B[i][j]);
					}
					else
					{
						blackAlive.add(B[i][j]);
					}
				}
			}
		}

		if(isPawnMove && promotion(r_,c_))
		{
			return 1;

		}

		return 0;
	}





	public boolean movePiece(int r, int c, int r_, int c_)
	{
		/**Returns true if it is possible to move piece from (r, c) to (r_, c_)*/

		
		String piece = B[r][c];


		int player = (B[r][c].charAt(0) - 'w' == 0)?0:1;

		boolean isPawn = (B[r][c].charAt(1) - 'p' == 0);

		boolean isKing = (B[r][c].charAt(1) - 'k' == 0);


		Vector<Vector<Integer>> trajectory = getTrajectory(B[r][c]);

		Iterator<Vector<Integer>> itr = trajectory.iterator();


		while(itr.hasNext())
		{
			Vector<Integer> cor = itr.next();


			if((int)cor.get(0) == r_ && (int)cor.get(1) == c_)
			{

				
				Boolean m = moved.get(piece);

				if(isKing)
				{
					if(m == false && isKing && Math.abs(c - c_) == 2 && noCheck(B[r][c], r, c))
					{
						if(c_ == 2)
						{
							moved.put(B[r][0], true);
							forceMove(r, 0, r, 3);
						}
						else if(c_ == 6)
						{
							moved.put(B[r][0], true);
							forceMove(r, 7, r, 5);
						}
					}
				}

				B[r_][c_] = B[r][c];
				B[r][c] = "";
				if(m == false)
				{
					moved.put(piece,true);
				}

				if(isPawn)
				{
					player = B[r_][c_].charAt(0);
					int index = Integer.parseInt("" + B[r_][c_].charAt(2));
					// 2 steps
					if(Math.abs(r_ - r) == 2)
					{
						if( (player - 'w') == 0)
						{whitePawns[index] = 3;}
						else
						{blackPawns[index] = 3;}
					}
					else if(Math.abs(r_ - r) == 1)
					{
						if( (player - 'w') == 0)
						{whitePawns[index] = 1;}
						else
						{blackPawns[index] = 1;}
					}
				}
				
				return true;
			}
		}
		return false;
	}

	public void forceMove(int r, int c, int r_, int c_)
	{
		String piece = B[r][c];

		B[r_][c_] = piece;
		moved.put(B[r_][c_], true);
		B[r][c] = "";


	}

	public boolean staleMate(int player)
	{
		
		if(this.count%2 == player && !Check(player))
	    {
	    	
	    	Iterator<String> itr = whiteAlive.iterator();

	    	HashSet<String >CopyAlive = new HashSet<String>();



	    	if(player == 1)
	    	{
	    		
	    		itr = blackAlive.iterator();
	    	}
	  


    		while(itr.hasNext())
    		{
    			CopyAlive.add(itr.next());

    		}

    		itr = CopyAlive.iterator();

    		while(itr.hasNext()){
    			String piece = itr.next();
  
    			Vector<Vector<Integer>> trajectory = getTrajectory(piece);

    			if(trajectory.size() > 0)
    			{
    				
    				return false;
    			}
    		}
    		
			return true;
		}

		
		return false;
	}

	public boolean checkMate(int player)
	{

		int counter = 0;
		boolean isCheck = Check(player);

	    if(isCheck)
	    {

  			int x = 0;
  			int y = 0;
  			Vector<Integer> Pos;
  			String king = "wk";
  			Iterator<String> Alive;

		    Pos = M.get("wk");
		    x = (int)Pos.get(0);
		    y = (int)Pos.get(1);
			Alive = whiteAlive.iterator();


  			if(player == 1)
  			{
  			    Pos = M.get("bk");
  			    x = (int)Pos.get(0);
  			    y = (int)Pos.get(1);
  			    king = "bk";
  			    Alive = blackAlive.iterator();
  			}


  			HashSet<String> CopyAlive = new HashSet<String>();

  			while(Alive.hasNext())
  			{
  				CopyAlive.add(Alive.next());
  			}

			
  			Alive = CopyAlive.iterator();
  			// we iterate through all the pieces and check for possible capture
			while(Alive.hasNext())
			{
				String piece = Alive.next();
				Vector<Vector<Integer>> trajectory = getNTrajectory(piece);
				Iterator<Vector<Integer>> itr = trajectory.iterator();
	
				while(itr.hasNext())
				{
					Vector<Integer> cor = itr.next();
					if(noCheck(piece, (int)cor.get(0), (int)cor.get(1)))
					{
						
						return false;
					}
				}
			}

			return true;
		}

		return false;
	}




	public boolean Check(int player)
	{

		
		Iterator<String> it;
		int i;
		int j;


		Vector<Integer> kingCord = M.get("wk");
		i = (int)kingCord.get(0);
		j = (int)kingCord.get(1);

		HashSet<String> ref = blackAlive;

		it = blackAlive.iterator();


		if(player == 1)
		{
			kingCord = M.get("bk");
			i = (int)kingCord.get(0);
			j = (int)kingCord.get(1);
			it = whiteAlive.iterator();
			ref = whiteAlive;


		}
		
		HashSet<String >CopyAlive = new HashSet<String>();
		CopyAlive.addAll(ref);
		
	
		it = CopyAlive.iterator();
	 	
	   while(it.hasNext())
	   {
	   		
	   		String loopThroughPiece = it.next();
	   		
			Vector<Vector<Integer>> v = getNTrajectory(loopThroughPiece);
			Iterator <Vector<Integer>> itr =  v.iterator();


			while(itr.hasNext())
			{
				Vector<Integer> C = itr.next();

				if( (int)C.get(0) == i && (int)C.get(1)== j )
				{
					return true;
				}
			}
	   	}
	
		return false;
	}

	public boolean noCheck(String piece, int r_, int c_)
	{

		/** Checks if moving the piece to r_ c_ results in a check
			returns true if the move results in a check
		*/

		
		char p = piece.charAt(0);
		int player = ((p - 'w') == 0)? 0 : 1;

		String capturedPiece = B[r_][c_];
		

		if(!capturedPiece.equals(""))
		{
			if(capturedPiece.charAt(0) - 'w' == 0)
			{
				whiteAlive.remove(capturedPiece);
			}
			else
			{
				blackAlive.remove(capturedPiece);
			}
		}

	
		Vector<Integer> Cor = M.get(piece);
		int r = (int)Cor.get(0);
		int c = (int)Cor.get(1);


		B[r_][c_] = B[r][c];
		B[r][c] = "";


		Vector<Integer> temp = new Vector<Integer>();
		temp.add(r_);temp.add(c_);
		this.M.put(B[r_][c_], temp);


		boolean result = !(Check(player));

		B[r_][c_] = capturedPiece;
		B[r][c] = piece;

		temp = new Vector<Integer>();
		temp.add(r);temp.add(c);
		this.M.put(piece, temp);

		if(capturedPiece.equals("") == false){
			temp = new Vector<Integer>();
			temp.add(r_);temp.add(c_);
			this.M.put(capturedPiece, temp);
		}
		


		if(!capturedPiece.equals(""))
		{
			if(capturedPiece.charAt(0) - 'w' == 0)
			{
				whiteAlive.add(capturedPiece);
			}
			else
			{
				blackAlive.add(capturedPiece);
			}
		}
	
		return result;
	}

	public Vector<Vector<Integer>> getKingTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of King
		includes all possible moves that are valid and do not result in check
		*/
		

		Vector<Vector<Integer>> result = new Vector<Vector<Integer>>();
		int player = ((piece.charAt(0) - 'w') == 0)?0:1;

		Vector<Integer> Cor = M.get(piece);

		int r = (int)Cor.get(0);
		int c = (int)Cor.get(1);

		boolean castleLeft = canCastleSide(player, 0);
		boolean castleRight = canCastleSide(player, 1);


		int dY[] = new int[]{1, 0, -1};
		int dX[] = new int[]{1, 0, -1};

	

		for(int dy:dY)
		{
			for(int dx:dX)
			{

				int x = r + dx; int y = c + dy;

				if(x == r && y == c){continue;}

				

				boolean cond = isValid(x, y) && !filled(player, x, y);

				
				if(normal){cond = cond && noCheck(piece, x, y);}
				else {;}
				
				if(cond)
				{
					

					Iterator<Vector<Integer>> itr = result.iterator();
					while(itr.hasNext())
					{
						Vector<Integer> temp = itr.next();
						
					}
					
					Cor = new Vector<Integer>();
					Cor.add(x); Cor.add(y);
					result.add(Cor);
				}
			}
		}

		if(castleLeft)
		{
			Cor = new Vector<Integer>();
			Cor.add(r); Cor.add(c - 2);
			result.add(Cor);

		}
		if(castleRight)
		{
			Cor = new Vector<Integer>();
			Cor.add(r); Cor.add(c + 2);
			result.add(Cor);
		}

		
		Iterator<Vector<Integer>> itr = result.iterator();
		while(itr.hasNext())
		{
			Vector<Integer> temp = itr.next();
			
		}
					
		return result;

	}

	public Vector<Vector<Integer>> getTrajectory(String piece)
	{
		
		/**Returns the tragectory of the piece*/
		if(!piece.equals(""))
		{
			char type = piece.charAt(1);

			switch(type)
			{
				case 'k': return getKingTrajectory(piece, true);
				case 'q': return getQueenTrajectory(piece, true);
				case 'n': return getKnightTrajectory(piece, true);
				case 'b': return getBishopTrajectory(piece, true);
				case 'r': return getRookTrajectory(piece, true);
				case 'p': return getPawnTrajectory(piece, true);
			}
		}
		return new Vector<Vector<Integer>>();
	}

	public Vector<Vector<Integer>> getNTrajectory(String piece)
	{
		
		/**Returns the tragectory of the piece*/
		if(!piece.equals(""))
		{
			char type = piece.charAt(1);

			switch(type)
			{
				case 'k': return getKingTrajectory(piece, false);
				case 'q': return getQueenTrajectory(piece, false);
				case 'n': return getKnightTrajectory(piece, false);
				case 'b': return getBishopTrajectory(piece, false);
				case 'r': return getRookTrajectory(piece, false);
				case 'p': return getPawnTrajectory(piece, false);
			}
		}
		return new Vector<Vector<Integer>>();
	}



	public boolean isValidChessMove(int r, int c, int r_, int c_, int player)
	{
		
		if(empty(r,c))
		{
			return false;
		}
		else
		{
			char p = B[r][c].charAt(0);

			if(player == 0)
			{
				if(p - 'w' != 0)
				{
					return false;
				}
			}
			else if(player == 1)
			{
				if(p - 'b' != 0)
				{
					return false;
				}
			}

			Vector<Vector<Integer>> trajectory = getTrajectory(B[r][c]);
			Iterator<Vector<Integer>> itr = trajectory.iterator();

			boolean hasCoordinates = false;
			while(itr.hasNext())
			{
				Vector<Integer> cor = itr.next();
				if((int)cor.get(0) == r_ && (int)cor.get(1) == c_)
				{
					hasCoordinates = true;
					break;
				}

			}

			if(hasCoordinates && !empty(r_, c_))
			{
				if(B[r_][c_].charAt(1) - 'k' == 0)
				{
					return false;
				}
				return true;
			}

			return true;
		}
	}

	public boolean isValid(int x, int y)
	{
		/**Returns true if the piece occupied at x, y is in the board*/
		return (x >= 0 && x < 8) && (y >= 0 && y < 8);
	}

	public boolean filled(int player, int r, int c)
	{
		/**Returns true if the piece occupied at r, c has the same color as player*/
		char p = (player == 0)?'w':'b';

		if(isValid(r, c) && !B[r][c].equals("") && B[r][c].charAt(0) - p == 0)
		{
			return true;
		}
		return false;
	}

	public boolean empty(int r, int c)
	{
		/**Returns true if the piece occupied at r, c is empty*/
		return B[r][c].equals("");
	}

	public boolean promotion(int r_ , int c_)
	{
		
		String piece = B[r_][c_];
		int x = r_;
		char p = piece.charAt(0);
		if(p - 'w' == 0)
		{
			if(x == 7)
				return true;
		}
		else
		{
			if(x == 0)
				return true;
		}

		return false;
	}




	public boolean canCastle(int player)
	{
		return (canCastleSide(player, 0) || canCastleSide(player, 1));
	}

	public boolean canCastleSide(int player, int left)
	{
		String king = "wk";
		String rook = "wr0";
		if(left != 0)
		{
			rook = "wr1";
		}
		if(player != 0)
		{
			king = "bk";
			if(left == 0){
				rook = "br0";
			}
			else
			{
				rook = "br1";
			}
		}

		int limit = 3;
		int sign = -1;
		if(left != 0)
		{
			limit = 2;
			sign = 1;
		}
		boolean kingMoved = moved.get(king);
		boolean rookMoved = moved.get(rook);
		boolean rookAlive = (player == 0)? whiteAlive.contains(rook): blackAlive.contains(rook);

		if(!kingMoved && !rookMoved && rookAlive)
		{
			Vector<Integer> cor = M.get(king);
			int x = (int)cor.get(0);
			int y = (int)cor.get(1);
			for(int i = 1; i <= limit;i++)
			{
				if(!(isValid(x, y + sign*i) && empty(x, y + sign*i)))
				{
					return false;
				}
			}
			return true;
		}

		return false;
	}



	public Vector<Vector<Integer>> getPawnTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of pawn*/

		Vector<Vector<Integer>> result = new Vector<Vector<Integer>>();

		char p = piece.charAt(0);
		int player = ((p - 'w') == 0)? 0:1;
		int otherplayer = (player == 0)? 1:0;


		int ind = Integer.parseInt(""+piece.charAt(piece.length() - 1));

		Boolean m = moved.get(piece);

		int dY[] = new int[]{-1, 0, 1};
		int dX[] = new int[]{1};

		if(p -'w' == 0)
		{
			if(whitePawns[ind] == 0)
			{
				dX = new int[]{1, 2};
			}
			else if(whitePawns[ind] <= -1)
			{
				return result;
			}
		}
		else
		{
			dX = new int[]{-1};
			if(blackPawns[ind] == 0)
			{
				dX = new int[]{-1, -2};
			}
			else if(blackPawns[ind] <= -1)
			{
				return result;
			}
		}

		Vector<Integer> pos = M.get(piece);
		int x = (int) pos.get(0); int y = (int) pos.get(1);
		for(int i = 0; i < dX.length; i++)
		{
			int dx = dX[i];
			if(i == 0)
			{
				for(int dy: dY)
				{
					boolean cond = isValid(x + dx, y + dy);

					if(dy != 0)
					{
						cond = cond && filled(otherplayer, x+dx, y+dy);
					}
					else if(dy == 0)
					{
						cond = cond && empty(x + dx, y + dy);
					}



					if(normal){cond = cond&& noCheck(piece, x+dx, y+dy);}
					else{;}
					if(cond){
						if(dy == 0||(dy != 0 && filled(otherplayer, x+dx, y + dy)))
						{
							Vector<Integer> cor = new Vector<Integer>();
							cor.add(x + dx); cor.add(y + dy);
							result.add(cor);
						}
					}
				}
			}
			else if(isValid(x + dx, y)){
        		int sign = (p - 'w' == 0)?1:-1;
        		boolean cond = empty(x+sign, y) && empty(x+dx, y);
        		if(normal){cond = cond && noCheck(piece, x+dx, y);}
        		else{;}
				if(cond){
					Vector<Integer> cor = new Vector<Integer>();
					cor.add(x + dx); cor.add(y);
					result.add(cor);
				}
			}
		}

		// enpassent

		dY = new int[]{1, -1};
		int dx = (player==0)?1:-1;
		for(int dy: dY){
			boolean cond = isValid(x, y+dy) && filled(otherplayer, x, y+dy) && B[x][y+dy].charAt(1) - 'p' == 0;
			if(normal){cond = cond && noCheck(piece, x, y+dy);}
			else{;}
			if(cond)
			{
				ind = Integer.parseInt(""+B[x][y+dy].charAt(2));
				int val = (otherplayer == 0)?whitePawns[ind]:blackPawns[ind];
				if( isValid(x+dx, y+dy) && val == 3 && B[x][y+dy].charAt(1) - 'p' == 0)
				{
					Vector<Integer> cor = new Vector<Integer>();cor.add(x + dx); cor.add(y+dy);
					result.add(cor);
				}
			}
		}
		
		return result;
	}



	public Vector<Vector<Integer>> getRookTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of rook
		includes all possible moves that are valid and do not result in check
		*/

		
		Vector<Vector<Integer>> result = new Vector<Vector<Integer>>();

		int player = ((piece.charAt(0) - 'w') == 0)?0:1;
		int otherplayer = (player == 0)?1:0;
		Vector<Integer> Cor = M.get(piece);
		int r = (int)Cor.get(0);
		int c = (int)Cor.get(1);



		int x = r;
		int y = c;

		boolean aboveChecked = false;
		boolean belowChecked = false;
		boolean leftChecked = false;
		boolean rightChecked = false;

		while(!(aboveChecked && belowChecked && leftChecked && rightChecked))
		{
			if(!aboveChecked)
			{x--;}
			else if(!belowChecked)
			{x++;}
			else if(!leftChecked)
			{y--;}
			else if(!rightChecked)
			{y++;}
			else{break;}

			boolean cond = (normal)?isValid(x, y) && noCheck(piece, x, y): isValid(x, y);

			if(cond)
			{
				if(empty(x, y))
				{

					Vector<Integer> cor = new Vector<Integer>();
					cor.add(x);cor.add(y);
					result.add(cor);
				}
				else if(filled(player, x, y))
				{

					if(!aboveChecked) {aboveChecked = true;}
					else if(!belowChecked) {belowChecked = true;}
					else if(!leftChecked){leftChecked = true;}
					else if(!rightChecked){rightChecked = true;}
					else{break;}
					x = r;
					y = c;
				}
				else if(filled(otherplayer, x, y)){

					Vector<Integer> cor = new Vector<Integer>();
					cor.add(x);cor.add(y);
					result.add(cor);
					if(!aboveChecked) {aboveChecked = true;}
					else if(!belowChecked) {belowChecked = true;}
					else if(!leftChecked){leftChecked = true;}
					else if(!rightChecked){rightChecked = true;}
					else{break;}
					x = r;
					y = c;
				}

			}
			if(x < 0){aboveChecked = true;x = r;y = c;}
			if(x > 8){belowChecked = true;x = r;y = c;}
			if(y < 0){leftChecked = true;x = r;y = c;}
			if(y > 8){rightChecked = true;x = r;y = c;}
		}
		
		return result;
	}


	public Vector<Vector<Integer>> getBishopTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of bishop
		includes all possible moves that are valid and do not result in check
		*/

		
		Vector<Vector<Integer>> result = new Vector<Vector<Integer>>();

		int player = ((piece.charAt(0) - 'w') == 0)?0:1;
		int otherplayer = (player == 0)?1:0;

		Vector<Integer> Cor = M.get(piece);
		int r = (int)Cor.get(0);
		int c = (int)Cor.get(1);


		int x = r;
		int y = c;


		boolean upperLeftChecked = false;
		boolean upperRightChecked = false;
		boolean lowerLeftChecked = false;
		boolean lowerRightChecked = false;

		while(!(upperLeftChecked && upperRightChecked && lowerLeftChecked  && lowerRightChecked))
		{
			if(!upperLeftChecked)
			{x--;y--;}
			else if(!upperRightChecked)
			{x--;y++;}
			else if(!lowerLeftChecked )
			{x++;y--;}
			else if(!lowerRightChecked)
			{x++;y++;}
			else{break;}

			boolean cond = (normal)?isValid(x, y) && noCheck(piece, x, y): isValid(x, y);

			if(cond)
			{
				if(empty(x, y))
				{

					Vector<Integer> cor = new Vector<Integer>();
					cor.add(x);cor.add(y);
					result.add(cor);
				}
				else if(filled(player, x, y))
				{

					if(!upperLeftChecked) {upperLeftChecked = true;}
					else if(!upperRightChecked) {upperRightChecked = true;}
					else if(!lowerLeftChecked){lowerLeftChecked = true;}
					else if(!lowerRightChecked){lowerRightChecked = true;}
					else{break;}
					x = r;
					y = c;
				}
				else if(filled(otherplayer, x, y)){

					Vector<Integer> cor = new Vector<Integer>();
					cor.add(x);cor.add(y);
					result.add(cor);
					if(!upperLeftChecked) {upperLeftChecked = true;}
					else if(!upperRightChecked) {upperRightChecked = true;}
					else if(!lowerLeftChecked){lowerLeftChecked = true;}
					else if(!lowerRightChecked){lowerRightChecked = true;}
					else{break;}
					x = r;
					y = c;
				}

			}
			if(x < 0 && y < 0){upperLeftChecked = true;x = r;y = c;}
			if(x < 0 && y > 8){upperRightChecked= true;x = r;y = c;}
			if(x > 8 && y < 0){lowerLeftChecked = true;x = r;y = c;}
			if(x > 8 && y > 8){lowerRightChecked = true;x = r;y = c;}
		}
		

		return result;
	}

	public Vector<Vector<Integer>> getQueenTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of Queen
		includes all possible moves that are valid and do not result in check
		*/

	

		Vector<Vector<Integer>> result = getBishopTrajectory(piece, normal);
		Vector<Vector<Integer>> toAdd = getRookTrajectory(piece, normal);

		Iterator<Vector<Integer>> itr = toAdd.iterator();
		while(itr.hasNext())
		{
			result.add(itr.next());
		}
		
		return result;
	}

	public Vector<Vector<Integer>> getKnightTrajectory(String piece, boolean normal)
	{
		/** Gets the trajectory of knight
		includes all possible moves that are valid and do not result in check
		*/

		
		char player = piece.charAt(0);


		Vector<Vector<Integer>> result = new Vector<Vector<Integer>>();

		Vector<Integer> cor = M.get(piece);

		int i = cor.get(0);
		int j = cor.get(1);


		int dX[] = new int[]{2, 1};
		int dY[] = new int[]{1, 2};

		for(int ind = 0; ind < dX.length; ind++)
		{
			int X[] = new int[]{i + dX[ind], i - dX[ind]};
			int Y[] = new int[]{j + dY[ind], j - dY[ind]};
			for(int a: X)
			{
				for(int b: Y){

					boolean cond = (normal)?isValid(a, b) && noCheck(piece, a, b):isValid(a, b);
					if(cond)
					{
						if(B[a][b].equals(""))
						{
							Vector<Integer> C = new Vector<Integer>();
							C.add(a);
							C.add(b);

							result.add(C);
						}
						else if(B[a][b].charAt(0) - player != 0)
						{
							Vector<Integer> C = new Vector<Integer>();
							C.add(a);
							C.add(b);

							result.add(C);
						}
					}
				}
			}
		}
		return result;
	}
}
