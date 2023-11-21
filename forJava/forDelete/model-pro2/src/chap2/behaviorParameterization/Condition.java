package chap2.behaviorParameterization;
public interface Condition {
    public boolean test(int n);
    default Condition and(Condition other){
        //return (n) -> this.test(n) && other.test(n);
        return new Condition(){
            @Override
            public boolean test(int n) {
                return this.test(n) && other.test(n);
            }
        };
    }
    default Condition or(Condition other){
        return (n) -> this.test(n) || other.test(n);
    }
    default Condition not(){//negate
        return (n) -> !this.test(n);
    }
}
