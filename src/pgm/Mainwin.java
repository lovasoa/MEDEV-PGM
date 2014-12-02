/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgm;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author zhaoshuli
 */
public class Mainwin extends javax.swing.JFrame {

  private JFileChooser fc;

  /**
   * Creates new form Mainwin
   */
  public Mainwin() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPane2 = new javax.swing.JSplitPane();
    jPanel5 = new javax.swing.JPanel();
    imageLabel = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    btnOuvrir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        .addContainerGap())
    );

    jSplitPane2.setRightComponent(jPanel5);

    btnOuvrir.setText("Ouvrir");
    btnOuvrir.setToolTipText("Ouvrir un fichier pgm");
    btnOuvrir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    
    final JToggleButton btnHistogramme = new JToggleButton("Histogramme");
    btnHistogramme.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent evt) {
    		if(btnHistogramme.isSelected()){
    			btnHistogrammeActionPerformed(evt);
    		} else {
    			displayImage(Main.opened.toImage());
    		}
    	}
    });

    
    final JToggleButton btnSeuillage = new JToggleButton("Seuillage");
    btnSeuillage.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		if (btnSeuillage.isSelected()){
    			Main.threshold();
    		} else {
    			displayImage(Main.opened.toImage());
    		}
    	}
    });
    
    JButton btnAggrandir = new JButton("Aggrandir");
    btnAggrandir.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent evt) {
    		Main.scaleInPlace(2.0);
    	}
    });
    
    btnRduire = new JButton("Réduire");
    btnRduire.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent evt) {
    		Main.scaleInPlace(0.5);
    	}
    });
    
    final JButton btnDifference = new JButton("Difference");
    btnDifference.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    	    JFileChooser fc = getFileChooser();
    	    int returnVal = fc.showOpenDialog(btnDifference.getParent());

    	    if (returnVal == JFileChooser.APPROVE_OPTION) {
    	      try {
    	        File file = fc.getSelectedFile();
    	        //This is where a real application would open the file.
    	        System.out.println("Opening: " + file.getName() + ".");
    	        Main.difference(file);
    	      } catch (IOException | UnsupportedOperationException e) {
    	        System.err.println("Unable to open file");
    	        e.printStackTrace();
    	      }
    	    } else {
    	      System.err.println("Open command cancelled by user.");
    	    }
    	}
    });

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6Layout.setHorizontalGroup(
    	jPanel6Layout.createParallelGroup(Alignment.LEADING)
    		.addGroup(jPanel6Layout.createSequentialGroup()
    			.addContainerGap()
    			.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
    				.addComponent(btnHistogramme, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(btnAggrandir, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
    				.addComponent(btnRduire, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
    				.addComponent(btnSeuillage, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
    				.addComponent(btnOuvrir, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
    				.addComponent(btnDifference, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
    			.addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
    	jPanel6Layout.createParallelGroup(Alignment.LEADING)
    		.addGroup(jPanel6Layout.createSequentialGroup()
    			.addComponent(btnOuvrir)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(btnHistogramme)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(btnSeuillage)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(btnAggrandir)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(btnRduire)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(btnDifference)
    			.addContainerGap(100, Short.MAX_VALUE))
    );
    jPanel6.setLayout(jPanel6Layout);

    jSplitPane2.setLeftComponent(jPanel6);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

private JFileChooser getFileChooser(){
    if (fc == null) fc = new JFileChooser();
    return fc;
  }
  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    JFileChooser fc = getFileChooser();
    int returnVal = fc.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        File file = fc.getSelectedFile();
        //This is where a real application would open the file.
        System.out.println("Opening: " + file.getName() + ".");
        Main.openFile(file);
      } catch (IOException | UnsupportedOperationException e) {
        System.err.println("Unable to open file");
        e.printStackTrace();
      }
    } else {
      System.err.println("Open command cancelled by user.");
    }
  }//GEN-LAST:event_jButton2ActionPerformed
  
  protected void btnHistogrammeActionPerformed(ActionEvent evt) {
	Main.histogram();
  }
  
  static void displayImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Mainwin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Mainwin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Mainwin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Mainwin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Mainwin().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private static javax.swing.JLabel imageLabel;
  private javax.swing.JButton btnOuvrir;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JSplitPane jSplitPane2;
  private JButton btnRduire;
}
