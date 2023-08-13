package com.example.quoters;

import java.util.Objects;

public class QuoteResource {

    private String type;
    private  Quote quote;

    public QuoteResource( Quote quote,String type) {
        this.type = type;
        this.quote = quote;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteResource that = (QuoteResource) o;
        return type.equals(that.type) && quote.equals(that.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, quote);
    }

    @Override
    public String toString() {
        return "QuoteResource{" +
                "type='" + type + '\'' +
                ", quote=" + quote +
                '}';
    }
}
