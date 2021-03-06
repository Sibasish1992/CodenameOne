/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores
 * CA 94065 USA or visit www.oracle.com if you need additional information or
 * have any questions.
 */
package com.codename1.designer;

import com.codename1.ui.util.EditableResources;
import com.codename1.ui.util.Resources;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

/**
 *
 * @author Shai Almog
 */
public class LivePreview extends javax.swing.JDialog {
    private static String previewKey;
    private static ResourceEditorView view;
    private static String themeSelection;
    private static String mainFormSelection = "";

    /**
     * @return the themeSelection
     */
    public static String getThemeSelection() {
        return themeSelection;
    }

    /**
     * @return the mainFormSelection
     */
    public static String getMainFormSelection() {
        return mainFormSelection;
    }

    /**
     * @return the previewKey
     */
    public static String getPreviewKey() {
        return previewKey;
    }

    
    public LivePreview(java.awt.Component c, ResourceEditorView v) {
        this((java.awt.Frame)SwingUtilities.windowForComponent(c), true, v);
    }

    /** Creates new form FindMultiImages */
    public LivePreview(java.awt.Frame parent, boolean modal, ResourceEditorView v) {
        super(parent, modal);
        view = v;
        initComponents();
        
        Resources res =  v.getLoadedResources();
        if(res != null) {
            Vector themeVec = new Vector();
            themeVec.add("[Native Theme]");
            themeVec.addAll(Arrays.asList(res.getThemeResourceNames()));
            themes.setModel(new DefaultComboBoxModel(themeVec));
            if(themeSelection != null && themeVec.contains(themeSelection)) {
                themes.setSelectedItem(themeSelection);
            }
            
            mainForm.setModel(new DefaultComboBoxModel(res.getUIResourceNames()));
            if(Arrays.asList(res.getUIResourceNames()).contains(mainFormSelection)) {
                mainForm.setSelectedItem(mainFormSelection);
            }
        }
        themes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(themes.getSelectedIndex() == 0) {
                    themeSelection = null;
                } else {
                    Resources res =  view.getLoadedResources();
                    themeSelection = res.getThemeResourceNames()[themes.getSelectedIndex() - 1];
                }
                if(getPreviewKey() != null) {
                    updateServer(LivePreview.this);
                }
            }
        });
        
        mainForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Resources res =  view.getLoadedResources();
                mainFormSelection = res.getUIResourceNames()[mainForm.getSelectedIndex()];
                if(getPreviewKey() != null) {
                    updateServer(LivePreview.this);
                }
            }
        });
        
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enableLivePreview = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closeDialog = new javax.swing.JButton();
        installClient = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mainForm = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        themes = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        signup = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Live Preview");
        setName("Form"); // NOI18N

        enableLivePreview.setToolTipText("Enable Live Preview");
        enableLivePreview.setName("enableLivePreview"); // NOI18N
        enableLivePreview.addActionListener(formListener);

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        closeDialog.setText("Close");
        closeDialog.setName("closeDialog"); // NOI18N
        closeDialog.addActionListener(formListener);
        jPanel2.add(closeDialog);

        installClient.setText("Install Client Software");
        installClient.setName("installClient"); // NOI18N
        jPanel2.add(installClient);

        jPanel1.add(jPanel2);

        jLabel1.setText("Main Form");
        jLabel1.setName("jLabel1"); // NOI18N

        mainForm.setName("mainForm"); // NOI18N

        jLabel2.setText("Theme");
        jLabel2.setName("jLabel2"); // NOI18N

        themes.setName("themes"); // NOI18N

        jLabel6.setText("Enable");
        jLabel6.setName("jLabel6"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        signup.setText("Signup for a full account at codenameone.com");
        signup.setName("signup"); // NOI18N
        signup.addActionListener(formListener);
        jPanel3.add(signup);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel6)
                            .add(jLabel2))
                        .add(73, 73, 73)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainForm, 0, 382, Short.MAX_VALUE)
                            .add(themes, 0, 382, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(enableLivePreview)
                                .add(354, 354, 354)))))
                .add(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(enableLivePreview)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(themes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(mainForm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == enableLivePreview) {
                LivePreview.this.enableLivePreviewActionPerformed(evt);
            }
            else if (evt.getSource() == closeDialog) {
                LivePreview.this.closeDialogActionPerformed(evt);
            }
            else if (evt.getSource() == signup) {
                LivePreview.this.signupActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

private void closeDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeDialogActionPerformed
    dispose();
}//GEN-LAST:event_closeDialogActionPerformed

public static void updateServer(final java.awt.Component parent) {    
}

public static void setLivePreviewEnabled(boolean e, java.awt.Component parent, ResourceEditorView v) {
}

private void enableLivePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableLivePreviewActionPerformed
    if(enableLivePreview.isSelected()) {
        if(previewKey == null) {
            previewKey = "";
            updateServer(this);
        }
    } else {
        previewKey = null;
    }
}//GEN-LAST:event_enableLivePreviewActionPerformed

private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("http://www.codenameone.com/build-server.html"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_signupActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeDialog;
    private javax.swing.JCheckBox enableLivePreview;
    private javax.swing.JButton installClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox mainForm;
    private javax.swing.JButton signup;
    private javax.swing.JComboBox themes;
    // End of variables declaration//GEN-END:variables
}
