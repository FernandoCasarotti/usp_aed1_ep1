package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class graph1 extends JPanel {

    int[][] data = { { 21, 0, 0, 1, 86, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 0, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 0, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 1, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 86, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 88, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 74, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 },
            { 87, 1, 3, 5, 234, 88, 74, 87, 54, 77, 61, 55, 48, 60, 49, 36, 38, 27, 20, 18 } };
    final int PAD = 20;

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD)); // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD)); // Draw
        // abcissa.

        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();

        // Ordinate label.
        String s = "data";
        float sy = PAD + ((h - 2 * PAD) - s.length() * sh) / 2 + lm.getAscent();

        for (int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float) font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw) / 2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }

        // Abcissa label.
        s = "x axis";
        sy = h - PAD + (PAD - sh) / 2 + lm.getAscent();
        float sw = (float) font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw) / 2;
        g2.drawString(s, sx, sy);

        // Draw lines.
        double xInc = (double) (w - 2 * PAD) / (data.length - 1);
        double scale = (double) (h - 2 * PAD) / getMax();
        g2.setPaint(Color.green.darker());

        for (int j = 0; j < data.length - 1; j++) {
            for (int i = 0; i < data[j].length - 1; i++) {
                double x1 = PAD + i * xInc;
                double y1 = h - PAD - scale * data[j][i];
                double x2 = PAD + (i + 1) * xInc;
                double y2 = h - PAD - scale * data[j][i + 1];
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

            // Mark data points.
            g2.setPaint(Color.red);
            for (int i = 0; i < data.length; i++) {
                double x = PAD + i * xInc;
                double y = h - PAD - scale * data[j][i];
                g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
            }
        }
    }

    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for (int j = 0; j < data.length - 1; j++) {
            for (int i = 0; i < data[j].length; i++) {
                if (data[j][i] > max)
                    max = data[j][i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new graph1());
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
    }
}