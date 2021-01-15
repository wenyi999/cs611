import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Market {//the market entity
    List<Item> itemsForSale=new ArrayList<>();
    public Market(){
        for (int i=0;i<6;i++){//6 kinds of items in all
            String[] itemList =getItemFiles(i);
            for (int j=1;j<itemList.length;j++){
                switch (i){
                    case 1->itemsForSale.add(new Armor(itemList[j]));
                    case 2->itemsForSale.add(new Spell(itemList[j], 1));
                    case 3->itemsForSale.add(new Spell(itemList[j], 2));
                    case 4->itemsForSale.add(new Spell(itemList[j], 3));
                    case 5->itemsForSale.add(new Potion(itemList[j]));
                    default->itemsForSale.add(new Weapon(itemList[j]));
                }
            }
        }
    }
    private String[] getItemFiles(int itemType){//get all items from separate files
        GameTool host=new GameTool();
        String filePath;
        switch (itemType) {
            case 1 -> filePath = "Legends_Monsters_and_Heroes/Armory.txt";
            case 2 -> filePath = "Legends_Monsters_and_Heroes/FireSpells.txt";
            case 3 -> filePath = "Legends_Monsters_and_Heroes/IceSpells.txt";
            case 4 -> filePath = "Legends_Monsters_and_Heroes/LightningSpells.txt";
            case 5 -> filePath = "Legends_Monsters_and_Heroes/Potions.txt";
            default -> filePath = "Legends_Monsters_and_Heroes/Weaponry.txt";
        }
        Stream<String> items =host.FileImporter(filePath);
        return items.filter(s -> s.length()>0).toArray(String[]::new);
    }

    @Override
    public String toString() {
        StringBuilder ans= new StringBuilder();
        for (Item item:itemsForSale){
            ans.append(item.toString()).append("\n");
        }
        return "itemsForSale=\n" + ans ;
    }
}
