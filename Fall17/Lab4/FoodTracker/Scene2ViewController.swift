//
//  Scene2ViewController.swift
//  FoodTracker
//
//  Created by Keke Wu on 10/12/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var userBreakfast: UITextField!
    @IBOutlet weak var userLunch: UITextField!
    @IBOutlet weak var userDinner: UITextField!
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneFood"{
        let scene1ViewController = segue.destination as! ViewController
            if userBreakfast.text!.isEmpty == false{
            scene1ViewController.user.breakfastRec=userBreakfast.text
            }
            if userLunch.text!.isEmpty == false{
            scene1ViewController.user.lunchRec=userLunch.text
            }
            if userDinner.text!.isEmpty == false{
            scene1ViewController.user.dinnerRec=userDinner.text
            }
        }
    }
    
    
    
    override func viewDidLoad() {
        userBreakfast.delegate=self
        userLunch.delegate=self
        userDinner.delegate=self
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
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
