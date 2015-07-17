package com.wallet.common.util;

import java.util.HashMap;
import java.util.Random;



public class LuktUtil
{
	int total;
	String[] p_data=null;
	
	public  LuktUtil(int i_total) {
		p_data = new String[i_total];
		total = i_total;
		//�����͸� �ʱ�ȭ �Ѵ�. 
		for(int i = 0; i < p_data.length; i++)
			p_data[i] = "0";		
	}

	// �ϳ��� ��ǰ�� ��ü ��ǰ�� ������ ���� �����Ѵ�. 
	public String make_lukt(int cnt, String pid)
	{
		int succ = 0;
		int	next_idx;
		int mrate = (int) Math.floor(total/ cnt);
		int i =0;
		int width = 0;
		
		Random r = new Random( );
		
		// �� Ȯ���� ��� ��÷�� random ���� ó�� �Ѵ�.
		int start  = 0;
		int  pos  = (int)(total / mrate);
		
		if( pos < 2) start  = ( (int)( total /3 )  * 2 ) % total ;
		else if( pos < 4) start = ( (int)( total /6 )  * 2 ) % total ;
		else if( pos < 6) start = ( (int)( total /8 )  * 2 ) % total ;
		else if( pos < 8) start = ( (int)( total /10 )  * 2 ) % total ;
		else if( pos < 10) start = ( (int)( total /12 )  * 2 ) % total ;
		else start  =0;
		
		Log.info("make start  " + total + "my cnt="+cnt+"// ��÷ ���� "+ mrate + "pos =" + pos +"//start="+ start);
		
		if( pos < 5)
		{
			for(succ =0 ; succ < cnt ; )
			{
				int m = (int) (Math.abs( r.nextInt( ) % total ));

				if( m < start) continue;
				
				if(p_data[m].equals("0"))
				{
					p_data[m] = pid;
					Log.info("make =" +m );
					succ ++;
				}
			}
			return "S";
		}
		
		width = total / pos;
		if(width < 10 ) width = total;
		
		for(i =0; i < total; i ++)
		{
			if(i != 1)
			{
				if(i % mrate == 0) // ������ ������
				{
					if(succ == cnt) break;
					
					if(p_data[i].equals("0"))
					{
		//				if( i < 2 ) 
						{
							int tm = ( i + (int) (Math.abs( r.nextInt( ) % width )) ) % total ;
							if(tm > total) continue;
							
							if(p_data[tm].equals("0"))
							{
								p_data[tm] = pid;
								Log.info("make =" +tm );
								succ ++;
								continue;
							}
						
							tm = ( i - (int) (Math.abs( r.nextInt( ) % width )) ) % total ;
							if( tm < 0) continue;
							if(p_data[tm].equals("0"))
							{
								p_data[tm] = pid;
								Log.info("make =" +tm );
								succ ++;
								continue;
							}
						}
						p_data[i] = pid;
						Log.info("make =" +i );
						succ ++;
					}
					else
					{
						// �ٸ� ��ǰ�� ��÷ �Ǿ� ������ ������ �������� �̵��Ѵ�.
						if(mrate < 5)            next_idx = i+ (int) (Math.abs( r.nextInt( ) % (total/8) ));
						else if(mrate < 10)   next_idx = i+ (int) (Math.abs( r.nextInt( ) % (total/15) ));
						else if(mrate < 100) next_idx = i+(mrate/5);
						else if(mrate < 200) next_idx = i+(mrate/4);
						else if(mrate < 400) next_idx = i+(mrate/3);
						else next_idx = i+(mrate/2);
						
						for(int j = next_idx; j < (i+mrate); j++)
						{
							if(p_data[j].equals("0"))
							{
								p_data[j] = pid;
								Log.info("make =" + j );
								succ ++;
								break;
							}
						}	
					}
				}
			}
		}
		
		Log.info("finish success step1 " + succ);
		
		// ��û�� ��ǰ�� ���� �߱��� ���� ���Ͽ��� ��� �ڿ��� �ٽ� ã�´�.
		if(succ != cnt)
		{
			if(mrate < 10) next_idx = 1;
			else if(mrate < 100) next_idx = (mrate/5);
			else if(mrate < 200) next_idx = (mrate/4);
			else if(mrate < 400) next_idx = (mrate/3);
			else next_idx = (mrate/2);
			
			for(int j = total -1; j >=0; j--)
			{
				if(p_data[j].equals("0"))
				{
					p_data[j] = pid;
					Log.info("make =" +j );
					succ ++;
					if(succ == cnt) break;
					j = j - next_idx;
				}
			}
		}
		
		
		
		Log.info("finish success step2 " + succ);
		// ������ ���� Ȯ��
		if(succ != cnt)
		{
			for(int j = total -1; j >=0; j--)
			{
				if(p_data[j].equals("0"))
				{
					p_data[j] = pid;
					succ ++;
					if(succ == cnt) break;
				}
			}
			
		}
		Log.info("finish success step3 " + succ);
		return "S";
	}
	
	public void check_lukt()
	{
		int m;
		HashMap<String,String> tempMap = new HashMap<String,String>();
		
		for(int i = 0; i < p_data.length; i++)
		{
			if(p_data[i].equals("0")) ;
			else 
			{
				try{
					m = Integer.parseInt(tempMap.get(p_data[i]).toString());
				}catch (Exception e) {
					m = 0;
				};
				
				m++;
				tempMap.put(p_data[i], ""+m);
			}
		}
		Log.info("check finished "+ tempMap);
	}
	
	public String[] get_lukt()
	{
		return p_data;
	}
	

}