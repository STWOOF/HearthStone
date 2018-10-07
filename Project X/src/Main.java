import java.util.*;


public class Main {
    int action=0;
    structsc[] k=new structsc[2];
    public static void main(String[] args) {
        new Main().start();
    }
 
    
    public Main() {
        k[0]=new structsc();
        k[1]=new structsc();
    }
 
    
    
    class structsc
    {
    	Servant[] sc=new Servant[8];//0代表英雄，1-7代表随从
        int scnum=0;//随从数量 如7个随从则scnum=7
        structsc()
        {
            sc[0]=new Servant();
        }
        public Servant getSc(int k) {
            return sc[k];
        }
 
        public void insertsc(int position, int health, int attack)
        {
        	Servant k=new Servant(health,attack);
            for (int i = scnum; i>position-1 ; i--) {
                sc[i+1]=sc[i];
            }
            sc[position]=k;
            scnum++;
        }
        
        public void insertscP(int position, int health, int attack)
        {
        	Servant k=new Servant(health,attack);
            for (int i = scnum; i>position-1 ; i--) {
                sc[i+1]=sc[i];
            }
            sc[position]=k;
            scnum++;
        	k.setPoision();
        	
        }
        
        
        public void delsc(int position)
        {
            for (int i = position; i < scnum; i++) {
                sc[i]=sc[i+1];
            }
            scnum--;
        }
        
        
        class Servant {
            int health=30;
            int attack=0;
            
            int poision=0;
            
            public void setPoision()
            {
            	this.poision = 1;
            }
            
            public int getPoision()
            {
            	return poision;
            }
            
            
            
            public void setHealth(int health) {
                this.health = health;
            }
 
            public int getHealth() {
                return health;
            }
 
            public int getAttack() {
                return attack;
            }
            Servant()
            {
                super();
            }
            Servant(int health, int attack)
            {
                this.attack=attack;
                this.health=health;
                poision=0;
            }
            
            public boolean islive()
            {
                if(this.health>0)
                    return true;
                else
                    return false;
            }
        }
        public  boolean isdead(){
            if(!sc[0].islive())
               return true;
            else
                return false;
 
        }
 
 
    }
    
    //i,i就是角色的位置
    public void attack(int i,int j,int attack,int defender )
    {
        k[i].getSc(attack).setHealth(k[i].getSc(attack).getHealth()-k[j].getSc(defender).getAttack());
        k[j].getSc(defender).setHealth(k[j].getSc(defender).getHealth()-k[i].getSc(attack).getAttack());
        
        
        if( !k[i].getSc(attack).islive())
        {    k[i].delsc(attack);
        }
        
        else if(k[j].getSc(defender).getPoision()==1)
        {
        	k[i].delsc(attack);
        }
        
        if(defender!=0) {
            if (!k[j].getSc(defender).islive())
                k[j].delsc(defender);
            
            else if(k[j].getSc(attack).getPoision()==1)
            {
            	k[i].delsc(defender);
            }
        }
    }
    
    
    public void start()
    {
        Scanner s=new Scanner(System.in);
        int num=s.nextInt();
        for (int i = 0; i <num ; i++) {
            String str=s.next();
            int position;
            int attack;
            int health;
            int poision;
            switch (str)
            {
                case "summon":
                    position=s.nextInt();
                    attack=s.nextInt();
                    health=s.nextInt();
                    k[action].insertsc(position,health,attack);
                    break;
                case "attack":
                    int attacker=s.nextInt();
                    int defender=s.nextInt();
                    attack(action,1-action,attacker,defender);
                    break;
                case "end":
                    action=1-action;
                    break;
                case "summonP":
                	position=s.nextInt();
                    attack=s.nextInt();
                    health=s.nextInt();
                    k[action].insertscP(position,health,attack);
                    break;
            }
        }
        if(k[1].isdead())
            System.out.println("1");
        else if(k[0].isdead())
            System.out.println("-1");
        else
            System.out.println("0");
        System.out.println(k[0].getSc(0).getHealth());
        System.out.print(k[0].scnum+" ");
        for (int i = 0; i <k[0].scnum ; i++) {
            System.out.print(k[0].getSc(i+1).getHealth()+" ");
        }
        System.out.println();
        System.out.println(k[1].getSc(0).getHealth());
        System.out.print(k[1].scnum+" ");
        for (int i = 0; i <k[1].scnum ; i++) {
            System.out.print(k[1].getSc(i+1).getHealth()+" ");
        }
    }
}
