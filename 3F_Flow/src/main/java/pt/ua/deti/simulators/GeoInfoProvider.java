/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.simulators;

import java.io.File;
import java.io.IOException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 * Data type that provides Geographic Information.
 *
 * @author Claudia Cardoso <claudiacardoso@ua.pt>
 * @author Sara Figueiredo <scfigueiredo@ua.pt>
 * @author Joao Silva <jpss@ua.pt>
 */
public class GeoInfoProvider {

    private static GeoInfoProvider geoInfoProvider = null;
    private static final String databasePath = "";
    private MapContent map;

    private GeoInfoProvider() throws IOException {
        File file = new File(databasePath);
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");

        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

    }

    public static GeoInfoProvider getInstance() throws IOException {
        if (geoInfoProvider != null) {
            geoInfoProvider = new GeoInfoProvider();
        }

        return geoInfoProvider;
    }

    public void showMap() {
        // Now display the map
        JMapFrame.showMap(map);
    }
    
    public void saveGeoInfo(double x1, double y1, double x2, double y2, double resolution, String resultFilePath){
        
    }
    
}
