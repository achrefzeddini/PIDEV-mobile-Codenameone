/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.esprit.pidevMobile.models.Event;
import com.esprit.pidevMobile.services.ServiceEvent;
import com.esprit.pidevMobile.services.ServiceMyEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jha
 */
public class MyEventsForm extends Form{
private Resources theme;
    public MyEventsForm() throws ParseException {
        super("My Events");
        theme = UIManager.initFirstTheme("/theme");
        ServiceMyEvent se = new ServiceMyEvent();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        List<Event> le = new ArrayList<Event>();
        
        le = se.getListMyEvents();
        Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Event e : le) { 	
                
                Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                
                
                EncodedImage enc = EncodedImage.createFromImage(theme.getImage("bla.jpg"), false);
                String urlim = "http://127.0.0.1/ch/web/img/"+e.getImage();
                URLImage urlimage = URLImage.createToStorage(enc, e.getTitre(), urlim);
                ImageViewer iv = new ImageViewer(urlimage);
                
                c.add(iv);
                MultiButton l = new MultiButton(e.getTitre());  
                l.setTextLine2(e.getHdebut()+"-"+e.getHfin());
                l.setTextLine3(""+e.getNbrplaces());
                l.setTextLine4("Prix : "+e.getPrix());
                c.add(l);
                cont.add(c);
                
                
        }
        add(cont);
        
    }
    
}
