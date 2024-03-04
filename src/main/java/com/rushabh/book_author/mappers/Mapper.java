package com.rushabh.book_author.mappers;

public interface Mapper <A,B>{
    B mapTo(A a);
    A mapFrom(B b);
}
