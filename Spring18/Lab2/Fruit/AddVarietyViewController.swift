//
//  AddVarietyViewController.swift
//  Fruit
//
//  Created by Keke Wu on 2/17/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class AddVarietyViewController: UIViewController {
    
    var addedVariety = String()

    @IBOutlet weak var varietyTextfield: UITextField!
   
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // Add BG image for UIView Controller
        let imageView = UIImageView(frame: self.view.bounds)
        imageView.image = UIImage(named:"bg3")
        self.view.addSubview(imageView)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneSegue"{
            //only add a variety if there is text in the textfield
            if((varietyTextfield.text?.isEmpty) == false){
                addedVariety = varietyTextfield.text!
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
