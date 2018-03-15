//
//  AddViewController.swift
//  Mid_keke
//
//  Created by Keke Wu on 3/15/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class AddViewController: UIViewController {

    @IBOutlet weak var nameLabel: UITextField!
    @IBOutlet weak var urlLabel: UITextField!
    
    var addedRes = String()
    var addedURL = String()
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "saveSegue"{
            //only add a country if there is text in the textfield
            if ((nameLabel.text?.isEmpty) == false){
                addedRes=nameLabel.text!
                addedURL = urlLabel.text!
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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
