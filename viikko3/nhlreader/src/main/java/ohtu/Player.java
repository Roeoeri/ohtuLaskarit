
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String goals;
    private String team;
    private String assists;
    private String nationality;
    
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
    
    public String getNationality(){
        return this.nationality;
    }
    
    public void setAssists(String assists){
        this.assists = assists;
    }
    
    public String getAssists(){
        return this.assists;
    }
    
    public void setTeam(String team){
        this.team = team;
    }
    
    public String getTeam(){
        return this.team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setGoals(String goals){
        this.goals = goals;
    }
    
    public String getGoals(){
        return this.goals;
    }

    @Override
    public String toString() {
        int total = Integer.parseInt(this.goals) + Integer.parseInt(this.assists);
        return name + " Team: "  + team + " Goals: " + goals + " Assists " + assists + " Total: " + total;
    }
    
    @Override
    public int compareTo(Player other){
        int playerTotal = Integer.parseInt(this.goals) + Integer.parseInt(this.assists);
        int otherTotal = Integer.parseInt(other.getGoals()) + Integer.parseInt(other.getAssists());
        if(otherTotal < playerTotal){
            return -1;
        }
         if(otherTotal > playerTotal){
            return 1;
        }
         
        return 0;
        
    }
      
}
