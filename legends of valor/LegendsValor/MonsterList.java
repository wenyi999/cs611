
import java.util.ArrayList;
import java.util.List;

public class MonsterList {
	
	private List<Dragon> dragonList;
	
	private List<Exoskeleton> exoskeletonList;
	
	private List<Spirit> spiritList;
	
	
	public MonsterList() {
		this.setDragonList(new ArrayList<Dragon>());
		this.setExoskeletonList(new ArrayList<Exoskeleton>());
		this.setSpiritList(new ArrayList<Spirit>());
		
		this.initDragons();
		this.initESkeletons();
		this.initSpirits();
	}
	
	
	
	public void initDragons() {
		
		 Dragon desghidorrah = new Dragon("Desghidorrah",3,300,400,35,3,300);
		 Dragon chrysophylax = new Dragon("Chrysophylax",2,200,500,20,2,200);
		 Dragon bunsenBurner = new Dragon("BunsenBurner",4,400,500,45,4,400);
		 Dragon natsunomeryu = new Dragon("Natsunomeryu",1,100,200,10,1,100);
		 Dragon theScaleless = new Dragon("TheScaleless",7,700,600,75,7,700);
		 Dragon kas_Ethelinh = new Dragon("Kas-Ethelinh",5,600,500,60,5,500);
		 Dragon alexstraszan = new Dragon("Alexstraszan",10,1000,900,55,10,1000);
		 Dragon phaarthurnax = new Dragon("Phaarthurnax",6,600,700,60,6,600);
		 Dragon d_Maleficent = new Dragon("D-Maleficent",9,900,950,85,9,900);
		 Dragon theWeatherbe = new Dragon("TheWeatherbe",8,800,900,80,8,800);
		 Dragon igneel = new Dragon("Igneel",6,600,400,60,6,600);
		 Dragon blueEyesWhite = new Dragon("BlueEyesWhite",9,900,600,75,9,900);
		 
		 Dragon[] dlist = new Dragon[] {desghidorrah,chrysophylax,bunsenBurner,natsunomeryu,theScaleless,kas_Ethelinh,
				                        alexstraszan,phaarthurnax,d_Maleficent,theWeatherbe,igneel,blueEyesWhite};
		 
		 this.addMultipleDragons(dlist);
		 
	}
	
	
	public void initESkeletons() {
		
		 Exoskeleton cyrrollalee = new Exoskeleton("Cyrrollalee",7,700,800,75,7,700);
		 Exoskeleton brandobaris = new Exoskeleton("Brandobaris",3,350,450,30,3,300);
		 Exoskeleton bigBad_Wolf = new Exoskeleton("BigBad-Wolf",1,150,250,15,1,100);
		 Exoskeleton wickedWitch = new Exoskeleton("WickedWitch",2,250,350,25,2,200);
		 Exoskeleton aasterinian = new Exoskeleton("Aasterinian",4,400,500,45,4,400);
		 Exoskeleton chronepsish = new Exoskeleton("Chronepsish",6,650,750,60,6,600);
		 Exoskeleton kiaransalee = new Exoskeleton("Kiaransalee",8,850,950,85,8,800);
		 Exoskeleton st_Shargaas = new Exoskeleton("St-Shargaas",5,550,650,55,5,500);
		 Exoskeleton merrshaullk = new Exoskeleton("Merrshaullk",10,1000,900,55,10,1000);
		 Exoskeleton st_Yeenoghu = new Exoskeleton("St-Yeenoghu",9,950,850,90,9,900);
		 Exoskeleton docOck = new Exoskeleton("DocOck",6,600,600,55,6,600);
		 Exoskeleton exodia = new Exoskeleton("Exodia",10,1000,1000,50,10,1000);
		 
		 Exoskeleton[] elist = new Exoskeleton[] {cyrrollalee,brandobaris,bigBad_Wolf,wickedWitch,aasterinian,chronepsish,kiaransalee,
				                                  st_Shargaas,merrshaullk,st_Yeenoghu,docOck,exodia};

         this.addMultipleESkeletons(elist);
	 
	}
	
	public void initSpirits() {
		
		   Spirit andrealphus = new Spirit("Andrealphus",2,600,500,40,2,200);
		   Spirit aim_Haborym = new Spirit("Aim-Haborym",1,450,350,35,1,100);
		   Spirit andromalius = new Spirit("Andromalius",3,550,450,25,3,300);
		   Spirit chiang_shih = new Spirit("Chiang-shih",4,700,600,40,4,400);
		   Spirit fallenAngel = new Spirit("FallenAngel",5,800,700,50,5,500);
		   Spirit ereshkigall = new Spirit("Ereshkigall",6,950,450,35,6,600);
		   Spirit melchiresas = new Spirit("Melchiresas",7,350,150,75,7,700);
		   Spirit jormunngand = new Spirit("Jormunngand",8,600,900,20,8,800);
		   Spirit rakkshasass = new Spirit("Rakkshasass",9,550,600,35,9,900);
		   Spirit taltecuhtli = new Spirit("Taltecuhtli",10,300,200,50,10,1000);
		   Spirit casper = new Spirit("Casper",1,100,100,50,1,100);
		   
		   
		   Spirit[] slist = new Spirit[] {andrealphus,aim_Haborym,andromalius,chiang_shih,fallenAngel,ereshkigall,melchiresas,
				                          jormunngand,rakkshasass,taltecuhtli,casper};
		   
		   this.addMultipleSpirits(slist);

	}
	
	
	// set and get method
	public void setDragonList(List<Dragon> dls) {
		this.dragonList = dls;
	}
	
	public List<Dragon> getDragonList(){
		return this.dragonList;
	}
	
	public void setExoskeletonList(List<Exoskeleton> els) {
		this.exoskeletonList = els;
	}
	
	public List<Exoskeleton> getExoskeletonList() {
		return this.exoskeletonList;
	}
	
	public void setSpiritList(List<Spirit> sls) {
		this.spiritList = sls;
	}
	
	public List<Spirit> getSpiritList(){
		return this.spiritList;
	}
	
	// methods handle dragon list
	
	public void addToDragons(Dragon d) {
		this.dragonList.add(d);
	}
	
	public void addMultipleDragons(Dragon[] ds) {
		for(Dragon d : ds) {
			this.addToDragons(d);
		}
	}
	
	public void removeFromDragons(Dragon d) {
		this.dragonList.remove(d);
	}
	
	// methods handle Exoskeleton list
	public void addToESkeletons(Exoskeleton es) {
		this.exoskeletonList.add(es);
	}
	
	public void addMultipleESkeletons(Exoskeleton[] esl) {
		for(Exoskeleton e : esl) {
			this.addToESkeletons(e);
		}
	}
	
	public void removeFromESkeletons(Exoskeleton es) {
		this.exoskeletonList.remove(es);
	}
	
	// methods handle Spirit list
	public void addToSpirits(Spirit s) {
		this.spiritList.add(s);
	}
	
	public void addMultipleSpirits(Spirit[] ss) {
		for(Spirit s : ss) {
			this.addToSpirits(s);
		}
	}
	
	public void removeFromSpirits(Spirit s) {
		this.spiritList.remove(s);
	}

}
