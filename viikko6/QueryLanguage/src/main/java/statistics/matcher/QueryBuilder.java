/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author htomi
 */
public class QueryBuilder {

    private Matcher mathcer;

    public QueryBuilder() {
        this.mathcer = new All();
    }

    public QueryBuilder playsIn(String team) {
        this.mathcer = new And(this.mathcer, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String type) {
        this.mathcer = new And(this.mathcer, new HasAtLeast(value, type));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String type) {
        this.mathcer = new And(this.mathcer, new HasFewerThan(value, type));
        return this;
    }

    public Matcher build() {
        Matcher completedMatcher = this.mathcer;
        this.mathcer = new All();
        return completedMatcher;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.mathcer = new Or(matchers);
        return this;
    }

}
