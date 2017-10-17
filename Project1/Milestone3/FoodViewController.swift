//
//  FoodViewController.swift
//  Daylight
//
//  Created by Keke Wu on 10/14/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class FoodViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var moodImage1: UIImageView!
    @IBOutlet weak var lunchTextfield: UITextField!
    @IBOutlet weak var breakfastTextfield: UITextField!
    @IBOutlet weak var dinnerTextfield: UITextField!
    
    var user = MealHighlight()
    var name: String?
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
         textField.resignFirstResponder()
         return true
    }
    
    override func prepare(for segue:UIStoryboardSegue, sender:Any?){
        if segue.identifier == "foodInfo"{
        let scene1ViewController = segue.destination as! RecordViewController
            scene1ViewController.newImage = moodImage1.image
            if breakfastTextfield.text!.isEmpty == false{
                scene1ViewController.user.breakfastHighlight = breakfastTextfield.text
                
                
            }
            if lunchTextfield.text!.isEmpty == false{
                scene1ViewController.user.lunchHighlight = lunchTextfield.text
            }
            if dinnerTextfield.text!.isEmpty == false{
                scene1ViewController.user.dinnerHighlight = dinnerTextfield.text
            }
            
        }
    }
    
    override func viewDidLoad() {
        breakfastTextfield.delegate = self
        lunchTextfield.delegate = self
        dinnerTextfield.delegate = self
        
        super.viewDidLoad()
        
        if name == "good" {
            moodImage1.image = UIImage(named:"Good")
        } else if name == "okay" {
            moodImage1.image = UIImage(named:"Okay")
        } else {
            moodImage1.image = UIImage(named:"Bad")
        }
      
       
  
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

    @IBAction func onTapGestureRecognized(_ sender: AnyObject) {
        breakfastTextfield.resignFirstResponder()
        lunchTextfield.resignFirstResponder()
        dinnerTextfield.resignFirstResponder()
    }
}
