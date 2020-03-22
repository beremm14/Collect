package main;

import data.Config;
import data.Database;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author emil
 */
public class Collect extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(Collect.class.getName());

    private final Object syncObj = new Object();
    
    public Collect() {
        initComponents();
        
        try {
            synchronized(syncObj) {
                loadConfig();
                syncObj.wait();
                loadDatabase();
                syncObj.wait();
            } 
        } catch (Exception ex) {
            LOG.severe(ex);
        }
    }
    
    private void display(String msg) {
        
    }
    
    private void loadConfig() throws IOException {
        File home;
        File folder;
        File configFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + ".Collect");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            configFile = new File(folder + File.separator + "Config.json");
        } else {
            configFile = new File("Config.json");
        }

        SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                synchronized (syncObj) {
                    try {
                        Config.getInstance().loadInto(new FileInputStream(configFile));
                    } catch (IOException ex) {
                        LOG.severe(ex);
                    }
                    syncObj.notifyAll();
                }
                return null;
            }
        };
        worker.execute();
    }
    
    private void saveConfig() throws IOException {
        File home;
        File folder;
        File configFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + ".Collect");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            configFile = new File(folder + File.separator + "Config.json");
        } else {
            configFile = new File("Config.json");
        }

        SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                try (BufferedWriter w = new BufferedWriter(new FileWriter(configFile))) {
                    Config.getInstance().writeTo(w);
                } catch (IOException ex) {
                    LOG.severe(ex);
                }
                return null;
            }
        };
        worker.execute();
    }
    
    private void loadDatabase() {
                SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                synchronized (syncObj) {
                    File file = new File(Config.getInstance().getDirectory());
                    Database.getInstance().loadInto(new FileInputStream(file));
                    Database.getInstance().setLoaded(true);
                    syncObj.notifyAll();
                }
                return null;
            }
        };
        worker.execute();
    }
    
        private void saveDatabase() throws IOException {
        SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                File file = new File(Config.getInstance().getDirectory());
                try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
                    Database.getInstance().writeTo(w);
                    LOG.info("Database saved");
                }
                return null;
            }
        };
        worker.execute();
    }
        
    private void saveDatabaseAs() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Json (*.json)", "json");
        chooser.setFileFilter(filter);
        chooser.setSelectedFile(new File(Config.getInstance().getDirectory()));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            SwingWorker<Object, Object> worker = new SwingWorker<Object, Object>() {
                @Override
                protected Object doInBackground() throws Exception {
                    try (BufferedWriter w = new BufferedWriter(new FileWriter(chooser.getSelectedFile()))) {
                        Database.getInstance().writeTo(w);
                        display("Datenbank exportiert...");
                        LOG.info("Database saved manually");
                    }
                    return null;
                }
            };
            worker.execute();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Collect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Collect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Collect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Collect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Collect().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
