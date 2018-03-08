//
//  AddToListViewController.swift
//  Happy Ivy
//
//  Created by Keke Wu on 3/5/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//  www.youtube.com/watch?v=gUhhFPTKCrE

import UIKit

class AddToListViewController: UIViewController {
    
    @IBOutlet weak var datePicker: UIDatePicker!
    @IBOutlet weak var imgImg: UIImageView!
    
    var image = UIImage()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set Background Image
        let backgroundImage = UIImageView(frame: UIScreen.main.bounds)
        backgroundImage.image = UIImage(named: "Timer_BG")
        backgroundImage.contentMode = UIViewContentMode.scaleAspectFill
        self.view.insertSubview(backgroundImage, at: 0)
        
        //Set BarButtonItem Color and Font
        UINavigationBar.appearance().tintColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0)
        let navigationFont = UIFont(name:"AveriaSansLibre-Regular",size:20)
        let navigationFontAttributes = [NSAttributedStringKey.font: navigationFont]
        UINavigationBar.appearance().titleTextAttributes = navigationFontAttributes
        UIBarButtonItem.appearance().setTitleTextAttributes(navigationFontAttributes, for: .normal)
        
        //DatePicker text color
        datePicker.setValue(UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0), forKey:"textColor")
        imgImg.image = image
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

 
}
