//
//  AddToListViewController.swift
//  Happy Ivy
//
//  Created by Keke Wu on 3/5/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//  www.youtube.com/watch?v=gUhhFPTKCrE
//  stackoverflow.com/questions/33788274/how-to-change-the-line-color-of-a-uidatepicker

import UIKit
class AddToListViewController: UIViewController {
    
    //Perform UnwindSegue
    @IBAction func cancleBtn(_ sender: UIBarButtonItem) {
        performSegue(withIdentifier: "unwindSegue", sender: self)
    }
    @IBOutlet weak var datePicker: UIDatePicker!
    @IBOutlet weak var imgImg: UIImageView!
    
    var image = UIImage()
    var imageName: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set Background Image
        let backgroundImage = UIImageView(frame: UIScreen.main.bounds)
        backgroundImage.image = UIImage(named: "Timer_BG")
        backgroundImage.contentMode = UIViewContentMode.scaleAspectFill
        self.view.insertSubview(backgroundImage, at: 0)
        
        //DatePicker text color
        datePicker.setValue(UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0), forKey:"textColor")
        
        //DatePicker Indicator color
        for subview in self.datePicker.subviews {
            if subview.frame.height <= 5 {
                subview.backgroundColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0)
                subview.tintColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0)
                subview.layer.borderColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0).cgColor
                subview.layer.borderWidth = 1            }
        }
        
        if let pickerView = self.datePicker.subviews.first {
            for subview in pickerView.subviews {
                if subview.frame.height <= 5 {
                    subview.backgroundColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0)
                    subview.tintColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0)
                    subview.layer.borderColor = UIColor.init(red:0.72 , green: 0.58, blue: 0.75, alpha: 1.0).cgColor
                    subview.layer.borderWidth = 1
                }
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let name = imageName{
            imgImg.image = UIImage(named: name)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
