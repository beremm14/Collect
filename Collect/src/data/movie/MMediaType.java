package data.movie;

/**
 *
 * @author emil
 */
public enum MMediaType {
    
    iTunes, BluRay, DVD, VHS, MP4, Netflix, Amazon, notDefined;

    public static MMediaType parseString(String input) {
        switch(input) {
            case "iTunes": return iTunes;
            case "BluRay": return BluRay;
            case "DVD": return DVD;
            case "VHS": return VHS;
            case "MP4": return MP4;
            case "Netflix": return Netflix;
            case "Amazon": return Amazon;
            default: return notDefined;
        }
    }
    
}
