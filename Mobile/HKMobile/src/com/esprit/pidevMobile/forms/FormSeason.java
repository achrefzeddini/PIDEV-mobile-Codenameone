/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.ToastBar.Status;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Season;
import com.esprit.pidevMobile.services.SeasonService;
import java.io.IOException;
import java.net.URISyntaxException;
import com.codename1.l10n.DateFormatPatterns;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author hp
 */
public class FormSeason extends Form {
   Form f;
    ImageViewer imgv;
    private Resources theme;
    
 public FormSeason(Form previous ) throws URISyntaxException, IOException {
    
        super("Hunting Seasons", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
         String url = "http://lh3.googleusercontent.com/proxy/cjU33j3WDfun6LP7Jq1JrfaqL_VUpMPKlrXQn904ogXCMYrFXQI1822ADUj6O2Z5ZdKgm9JH7YVErAevWzKKOOh11hdlnYKo1muv42TwrdyEIq5oixDsy3qOE0PdxU5_bYCyaU-oOgeGHrg";
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth,  deviceWidth, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragei",url, URLImage.RESIZE_SCALE);
//            EncodedImage enc = 
//                EncodedImage.
//                        createFromImage(
//                                theme.getImage("icon.png")
//                                ,false);
//          imgs = URLImage.createToStorage(enc, "fi.png", url, URLImage.RESIZE_SCALE);
//                
//                imgs = imgs.scaled(350, 350);
                imgv = new ImageViewer();
                imgv.setImage(i);
        SeasonService as = new SeasonService();
        ArrayList<Season> list = as.getAllSeasons();
 
this.add(imgv);
        for (Season an : list) {
  
         Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            MultiButton m = new MultiButton("Season : " +(an.getNom()));
                      
           
            SpanLabel start = new SpanLabel("Start : " +an.getStart());
            SpanLabel finish = new SpanLabel("Finish : "+an.getFinish());
            SpanLabel desc = new SpanLabel("Information :"+an.getDescription());
            Button btnupdate = new Button("Update");
            Button btnsupprimer = new Button("Delete");
            
            btnupdate.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 Form update = new Form(BoxLayout.y());
                 TextField nom = new TextField(an.getNom());
                 Picker start = new Picker();
                  start.setType(Display.PICKER_TYPE_DATE);
                  start.setDate(new Date());
                 Picker finish = new Picker();
                  finish.setType(Display.PICKER_TYPE_DATE);
                  finish.setDate(new Date());
                 TextField desc = new TextField(an.getDescription());
                 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new SimpleDateFormat(DateFormatPatterns.ISO8601);
                        
                        System.out.println(start.getDate());
                        System.out.println(finish.getDate());
                 Button updateseason = new Button("Update Season");
                        updateseason.addActionListener((evt1) -> {
         if ((nom.getText().length() == 0) || (desc.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else{
                     Season t = new Season(an.getId(), nom.getText(), start.getDate(), finish.getDate(), desc.getText());
                         new SeasonService().updateSeason(t) ;
                        Dialog.show("SUCCESS", "Season Updated ", "OK", null);
                        //               SMS              //
//                     Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json").
//        queryParam("To", "+21627898026").
//        queryParam("From", "+12513128911").
//        queryParam("Body", "The Hunting Season " + an.getNom() + " has been updated").
//        basicAuth(ACCOUNT_SID, AUTH_TOKEN).//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
//        getAsJsonMap();

                    {
                        
                         try {
                             new FormSeason(f).show();
                         } catch (URISyntaxException ex) {

                         } catch (IOException ex) {
                       
                         }
                    }
         }
           
        });
                        update.add(nom);
                        update.add(start);
                        update.add(finish);
                        update.add(desc);
                        update.add(updateseason);
                               update.getToolbar().addCommandToLeftBar("Return", null, (evt2) -> {
                     try {
                         new FormSeason(f).show();
                     } catch (URISyntaxException ex) {
                     } catch (IOException ex) {
                     }
                });
                               update.show();
             }
         });
            btnsupprimer.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                               if(Dialog.show("Confirmation", "Are you Sure you Want to Delete : " +an.getNom() , "OK", "Cancel")){
                            SeasonService as = new SeasonService();
                            as.supprimerSeason(an.getId());
//                            sendMail("achref.zeddini@esprit.tn");
                            Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Deleting "+ an.getNom());
            status.setShowProgressIndicator(true);
            status.setIcon(createIcon(FontImage.MATERIAL_DELETE));
            status.show();
            // Now do something that takes a while
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(2000);

            });
//                            Dialog.show("Suppression", "Season Successfully Deleted", "OK", null);
                           
                                   try {
                                       new FormSeason(f).show();
//f.refreshTheme();
                                   } catch (URISyntaxException ex) {
                                     
                                   } catch (IOException ex) {
                                      
                                   }
                            
                }

             }
         });
           

        
            C2.add(m);
            C2.add(start);
            C2.add(finish);
            C2.add(desc);
            C2.add(btnupdate);
            C2.add(btnsupprimer);
            
 
//            this.add(C2);
//            this.add(i);
            this.add(C2);

            
        }
     
//
//        this.add(new SpanLabel(new AnimalService().getAllAnimals().toString()));
this.getToolbar().addCommandToOverflowMenu("Add Season", null, (evt) -> {
            new FormNewSeason(this).show();
        });
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new HomeForm(theme).show();
        });
    }
 
    private Image createIcon(char charcode) {
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
//    public void sendMail(String Email) {
//        ConnectionRequest req = new ConnectionRequest();
//        req.setUrl("http://localhost/journal/sendmail.php?email="+ Email);
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//
//                byte[] data = (byte[]) evt.getMetaData();
//                String s = new String(data);
//                System.err.println("Mail Sent");
//            }
//        });
//
//        NetworkManager.getInstance().addToQueue(req);
//    }
}