/*
 * IConverter.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.base.converter;

/**
 * The interface class for converter
 *
 * @author hcnlinh
 * @version $Revision:  $
 * @param <T1> from type
 * @param <T2> to type
 */
public interface IConverter<T1, T2>
{

    /**
     * convert from <T1> to <T2>
     *
     * @param t1
     * @return T2
     */
    T2 convert(T1 t1);
    
    /**
     * Method description
     *
     * @param t1
     * @return T2
     */
    T2 convertImpl(T1 t1);
}


/*
 * Changes:
 * $Log: $
 */