/*
 * AbstractConverter.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.base.converter;

/**
 * The abstract class of converter
 *
 * @author hcnlinh
 * @version $Revision:  $
 * @param <T2>
 * @param <T1>
 */
abstract class AbstractConverter<T1, T2> implements IConverter<T1, T2>
{

    /**
     * @see research.vn.wcrl.base.converter.IConverter#convert(java.lang.Object)
     */
    public T2 convert(T1 t1)
    {
        if (t1 == null)
        {
            return null;
        }
        return convertImpl(t1);
    }

    /**
     * @see research.vn.wcrl.base.converter.IConverter#convertImpl(java.lang.Object)
     */
    public abstract T2 convertImpl(T1 t1);

}


/*
 * Changes:
 * $Log: $
 */