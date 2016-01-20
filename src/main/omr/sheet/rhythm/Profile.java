//------------------------------------------------------------------------------------------------//
//                                                                                                //
//                                          P r o f i l e                                         //
//                                                                                                //
//------------------------------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">
//  Copyright © Hervé Bitteur and others 2000-2016. All rights reserved.
//  This software is released under the GNU General Public License.
//  Goto http://kenai.com/projects/audiveris to report bugs or suggestions.
//------------------------------------------------------------------------------------------------//
// </editor-fold>
package omr.sheet.rhythm;

import omr.sig.inter.Inter;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Class {@code Profile}
 *
 * @author Hervé Bitteur
 */
public class Profile
{
    //~ Instance fields ----------------------------------------------------------------------------

    /** The sorted map of rhythm items. (typically within a stack) */
    private final SortedMap<Inter, Attrs> map = new TreeMap<Inter, Attrs>(Inter.byFullAbscissa);

    //~ Methods ------------------------------------------------------------------------------------
    //--------//
    // equals //
    //--------//
    @Override
    public boolean equals (Object obj)
    {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Profile)) {
            return false;
        }

        Profile that = (Profile) obj;

        if (this.map.size() != that.map.size()) {
            return false;
        }

        Iterator<Entry<Inter, Attrs>> thisIt = this.map.entrySet().iterator();
        Iterator<Entry<Inter, Attrs>> thatIt = that.map.entrySet().iterator();

        while (thisIt.hasNext()) {
            Entry<Inter, Attrs> thisEntry = thisIt.next();
            Entry<Inter, Attrs> thatEntry = thatIt.next();
            Inter thisKey = thisEntry.getKey();
            Inter thatKey = thatEntry.getKey();

            if (thisKey != thatKey) {
                return false;
            }

            Attrs thisAttrs = thisEntry.getValue();
            Attrs thatAttrs = thatEntry.getValue();

            if (thisAttrs == null) {
                if (thatAttrs != null) {
                    return false;
                }
            } else if (!thisAttrs.equals(thatAttrs)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode ()
    {
        int hash = 7;
        hash = (83 * hash) + Objects.hashCode(this.map);

        return hash;
    }

    //~ Inner Classes ------------------------------------------------------------------------------
    //-------//
    // Attrs //
    //-------//
    /**
     * Define the attributes assigned to a profile item.
     */
    public static class Attrs
    {
        //~ Methods --------------------------------------------------------------------------------

        @Override
        public boolean equals (Object obj)
        {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Attrs)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode ()
        {
            int hash = 5;

            return hash;
        }

        @Override
        public String toString ()
        {
            StringBuilder sb = new StringBuilder("[");
            sb.append(internals());
            sb.append(']');

            return sb.toString();
        }

        protected String internals ()
        {
            return "";
        }
    }

    //----------//
    // DotAttrs //
    //----------//
    public static class DotAttrs
            extends Attrs
    {
        //~ Instance fields ------------------------------------------------------------------------

        /** Number of dots for the item. */
        int dotCount;

        //~ Methods --------------------------------------------------------------------------------
        @Override
        public boolean equals (Object obj)
        {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof DotAttrs)) {
                return false;
            }

            DotAttrs that = (DotAttrs) obj;

            return this.dotCount == that.dotCount;
        }

        @Override
        public int hashCode ()
        {
            int hash = 3;
            hash = (29 * hash) + this.dotCount;

            return hash;
        }

        @Override
        protected String internals ()
        {
            StringBuilder sb = new StringBuilder(super.internals());
            sb.append(" d:").append(dotCount);

            return sb.toString();
        }
    }

    //--------------//
    // FlagDotAttrs //
    //--------------//
    public static class FlagDotAttrs
            extends DotAttrs
    {
        //~ Instance fields ------------------------------------------------------------------------

        /** Number of resulting flags for the item. */
        int flagCount;

        //~ Methods --------------------------------------------------------------------------------
        @Override
        public boolean equals (Object obj)
        {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof FlagDotAttrs)) {
                return false;
            }

            FlagDotAttrs that = (FlagDotAttrs) obj;

            return (this.flagCount == that.flagCount) && (this.dotCount == that.dotCount);
        }

        @Override
        public int hashCode ()
        {
            int hash = 7;
            hash = (89 * hash) + this.flagCount;

            return hash;
        }

        @Override
        protected String internals ()
        {
            StringBuilder sb = new StringBuilder(super.internals());
            sb.append(" f:").append(flagCount);

            return sb.toString();
        }
    }
}