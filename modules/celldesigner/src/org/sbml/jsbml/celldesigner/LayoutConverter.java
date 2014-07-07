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
package org.sbml.jsbml.celldesigner;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jp.sbi.celldesigner.plugin.PluginCompartment;
import jp.sbi.celldesigner.plugin.PluginReaction;
import jp.sbi.celldesigner.plugin.PluginSpecies;
import jp.sbi.celldesigner.plugin.PluginSpeciesAlias;
import jp.sbi.celldesigner.plugin.DataObject.PluginRealLineInformationDataObjOfReactionLink;

import org.sbml.jsbml.ext.layout.BoundingBox;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceRole;
import org.sbml.jsbml.ext.layout.TextGlyph;


/**
 * @author Ibrahim Vazirabad
 * @version $Rev$
 * @since 1.0
 * @date Jun 19, 2014
 */
public class LayoutConverter {

  final static double depth  =  1d;
  final static double z  =  0d;

  /**
   * Extracts Compartment Layout data from CellDesigner
   * @param pCompartment the PluginCompartment
   * @param layout The Layout object
   */
  public static void extractLayout(PluginCompartment pCompartment, Layout layout)
  {
    CompartmentGlyph cGlyph  =  layout.createCompartmentGlyph("cGlyph_" + pCompartment.getId());
    cGlyph.createBoundingBox(pCompartment.getWidth(), pCompartment.getHeight(), depth, pCompartment.getX(), pCompartment.getY(), z);

    TextGlyph tGlyph = layout.createTextGlyph("tGlyph_" + pCompartment.getId());
    BoundingBox bBox  =  tGlyph.createBoundingBox();
    bBox.createPosition(pCompartment.getNamePositionX(), pCompartment.getNamePositionY(), z);
    tGlyph.setOriginOfText(pCompartment.getId());
  }

  /**
   * Extracts Species Layout data from CellDesigner
   * @param pSpecies the PluginSpeciesAlias
   * @param layout The Layout object
   */
  public static void extractLayout(PluginSpeciesAlias pSpeciesAlias, Layout layout)
  {
    SpeciesGlyph sGlyph = layout.createSpeciesGlyph("sGlyph_" + pSpeciesAlias.getSpecies().getId());
    sGlyph.createBoundingBox(pSpeciesAlias.getWidth(), pSpeciesAlias.getHeight(), depth, pSpeciesAlias.getX(), pSpeciesAlias.getY(), z);

    TextGlyph tGlyph = layout.createTextGlyph("tGlyph_" + pSpeciesAlias.getSpecies().getId());
    BoundingBox bBox  =  tGlyph.createBoundingBox(pSpeciesAlias.getWidth(), pSpeciesAlias.getHeight(), depth);
    //setting textGlyph position at center of speciesGlyph
    bBox.createPosition(pSpeciesAlias.getX()+pSpeciesAlias.getWidth()/2, pSpeciesAlias.getY()+pSpeciesAlias.getHeight()/2, z);
    tGlyph.setOriginOfText(pSpeciesAlias.getSpecies().getId());
  }

  /**
   * Extracts Reaction Layout data from CellDesigner
   * @param pReaction  the PluginReaction
   * @param layout The Layout object
   */
  public static void extractLayout(PluginReaction pReaction, Layout layout)
  {
    //create ReactionGlyph
    layout.createReactionGlyph("rGlyph_"+pReaction.getId());
    PluginRealLineInformationDataObjOfReactionLink link = pReaction.getAllMyPostionInfomations();
    Vector<Object> speciesReferenceGlyphPieces = link.getPointsInLine();
    Vector<Object> reactionLinkMembers = link.getReactionLinkMembers();
    ArrayList<Double> reactionX = new ArrayList<Double>();
    ArrayList<Double> reactionY = new ArrayList<Double>();
    String s = "";
    for (Object d:speciesReferenceGlyphPieces)
    {
      s+=d.toString()+"\n";
    }
    s+="LINK: "+link.getMidPointInLine();
    //    for (Object o: reactionLinkMembers)
    //    {
    //      s+=((ReactionLink)o).toString()+"\n";
    //    }
    JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(pReaction.getId()+"\n"+s)));
    //create SpeciesReferenceGlyphs for reactants
    for (int i = 0;i<pReaction.getNumReactants();i++)
    {
      SpeciesReferenceGlyph srGlyph = new SpeciesReferenceGlyph("srGlyphReactant_"+pReaction.getId()+"_"+
          pReaction.getReactant(i).getSpeciesInstance().getId());
      srGlyph.setRole(SpeciesReferenceRole.SUBSTRATE);

      PluginSpecies pSpecies = pReaction.getReactant(i).getSpeciesInstance();
      srGlyph.setSpeciesGlyph("sGlyph_"+pSpecies.getId());
      layout.getReactionGlyph("rGlyph_"+pReaction.getId()).addSpeciesReferenceGlyph(srGlyph);
    }

    //create SpeciesReferenceGlyphs for products
    for (int i = 0;i<pReaction.getNumProducts();i++)
    {
      SpeciesReferenceGlyph srGlyph = new SpeciesReferenceGlyph("srGlyphProduct_" + pReaction.getId() + "_" +
          pReaction.getProduct(i).getSpeciesInstance().getId());
      srGlyph.setRole(SpeciesReferenceRole.PRODUCT);

      PluginSpecies pSpecies = pReaction.getProduct(i).getSpeciesInstance();

      srGlyph.setSpeciesGlyph("sGlyph_"+pSpecies.getId());
      layout.getReactionGlyph("rGlyph_"+pReaction.getId()).addSpeciesReferenceGlyph(srGlyph);
    }

    //create SpeciesReferenceGlyphs for modifiers
    for (int i = 0;i<pReaction.getNumModifiers();i++)
    {
      SpeciesReferenceGlyph srGlyph = new SpeciesReferenceGlyph("srGlyphModifier_" + pReaction.getId()+ "_" +
          pReaction.getModifier(i).getSpeciesInstance().getId());
      srGlyph.setRole(SpeciesReferenceRole.MODIFIER);

      PluginSpecies pSpecies = pReaction.getModifier(i).getSpeciesInstance();
      srGlyph.setSpeciesGlyph("sGlyph_"+pSpecies.getId());
      layout.getReactionGlyph("rGlyph_"+pReaction.getId()).addSpeciesReferenceGlyph(srGlyph);
    }

    TextGlyph tGlyph = layout.createTextGlyph("tGlyph_" + pReaction.getId());
    // tGlyph.createBoundingBox(15, 10, depth, reactionXCoordinate-10, reactionYCoordinate-10, z);
    tGlyph.setOriginOfText(pReaction.getId());
  }
}
