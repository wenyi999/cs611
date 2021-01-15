import java.util.ArrayList;
import java.util.Arrays;

public class World {//a world full of tiles with a team of heroes
    Tile[][] tiles;
    Team heroTeam;
    Market market=new Market();//all market tiles has same items for sell
    public World(){
        this(8);
    }
    public World(int d){
        this(d,d);
    }
    public World(int w,int h){
        tiles=new Tile[w][h];
        heroTeam=new Team();
        for (int i=0;i<w;i++){
            for (int j=0;j<h;j++){
                tiles[i][j]=new Tile();
            }
        }
        int i=0;int j=0;
        while (tiles[i][j].getTileType()==2){
            int sum=i*w+j+1;
            i=sum/h;
            j=sum%h;
        }
        tiles[i][j].setHasHero(true);
        tiles[i][j].setHeroName(String.valueOf(heroTeam.heroes[0].name.charAt(0)));
        tiles[i][j].heroAtThisTile= new ArrayList<>(Arrays.asList(heroTeam.heroes)) ;
        for (Hero hero : heroTeam.heroes){
            hero.x=i;
            hero.y=j;
        }
        System.out.println(this);
    }


    @Override
    public String toString() {
        StringBuilder ans= new StringBuilder();
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                if(tiles[i][j].getHasHero()){ans.append("| ").append(tiles[i][j].getHeroName().charAt(0)).append(" |");}
                else{ ans.append("| ").append(tiles[i][j].toString()).append(" |");}
            }
            ans.append("\n");
        }

        return ans.toString();
    }
}
