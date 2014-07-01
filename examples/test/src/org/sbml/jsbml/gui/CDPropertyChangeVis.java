/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 6. Marquette University, Milwaukee, WI, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


/**
 * @author Ibrahim Vazirabad
 * @version $Rev$
 * @since 1.0
 * @date Jul 1, 2014
 */
public class CDPropertyChangeVis extends JFrame implements ActionListener {
  /** Generated serial version identifier */
  private static final long serialVersionUID = -6800051247041441688L;
  Container contentPane = getContentPane();
  static JTextArea viewerArea = new JTextArea(50,120);
  static String propertyChangeString = "";

  public CDPropertyChangeVis() {
    super("SBML/CD Test");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    contentPane.add(new JScrollPane(viewerArea));
    pack();
    setLocationRelativeTo(null);
    setAlwaysOnTop(true);
    setVisible(true);
  }

  public static void addSBase(String sbase)
  {
    String sbaseAdded = "\nSBase_Added: "+sbase;
    JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sbase)));
    propertyChangeString += sbaseAdded;
    viewerArea.append(propertyChangeString);
  }

  public static void modelOpened(String sbase)
  {
    String modelOpened = "\nmodel_opened: "+sbase;
    JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sbase)));
    propertyChangeString += modelOpened;
    viewerArea.append(propertyChangeString);
  }

  public static void modelClosed(String sbase)
  {
    String modelClosed = "\nmodel_closed: "+sbase;
    JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sbase)));
    propertyChangeString += modelClosed;
    viewerArea.append(propertyChangeString);
  }

  public static void deleteSBase(String sbase)
  {
    String deleteSBase="\nsbase_deleted: "+sbase;
    JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sbase)));
    propertyChangeString += deleteSBase;
    viewerArea.append(propertyChangeString);
  }

  /** @param args Expects a valid path to an SBML file. */
  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    new CDPropertyChangeVis();
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}
