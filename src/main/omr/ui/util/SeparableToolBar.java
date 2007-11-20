//----------------------------------------------------------------------------//
//                                                                            //
//                      S e p a r a b l e T o o l B a r                       //
//                                                                            //
//  Copyright (C) Herve Bitteur 2000-2007. All rights reserved.               //
//  This software is released under the GNU General Public License.           //
//  Contact author at herve.bitteur@laposte.net to report bugs & suggestions. //
//----------------------------------------------------------------------------//
//
package omr.ui.util;

import javax.swing.*;

/**
 * Class <code>SeparableToolBar</code> is a tool bar which is able to collapse
 * unneeded separators
 *
 * @author Brenton Partridge
 * @version $Id$
 */
public class SeparableToolBar
    extends JToolBar
{
    //~ Constructors -----------------------------------------------------------

    public SeparableToolBar ()
    {
        super();
    }

    public SeparableToolBar (int orientation)
    {
        super(orientation);
    }

    public SeparableToolBar (String name)
    {
        super(name);
    }

    public SeparableToolBar (String name,
                             int    orientation)
    {
        super(name, orientation);
    }

    //~ Methods ----------------------------------------------------------------

    //--------------//
    // addSeparator //
    //--------------//
    /**
     * The separator will be inserted only if it is really necessary
     */
    @Override
    public void addSeparator ()
    {
        int count = super.getComponentCount();

        if ((count > 0) &&
            !(getComponent(count - 1) instanceof JSeparator)) {
            super.addSeparator();
        }
    }

    //----------------//
    // purgeSeparator //
    //----------------//
    /**
     * Remove any potential orphan separator at the end of the tool bar
     */
    public static void purgeSeparator (JToolBar toolBar)
    {
        int count = toolBar.getComponentCount();

        if (toolBar.getComponent(count - 1) instanceof JSeparator) {
            toolBar.remove(count - 1);
        }
    }
}