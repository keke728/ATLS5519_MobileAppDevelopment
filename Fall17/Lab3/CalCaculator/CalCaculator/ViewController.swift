//
//  ViewController.swift
//  CalCaculator
//
//  Created by Keke Wu on 10/8/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
   
    }

    @IBOutlet weak var Breakfast: UITextField!
    @IBOutlet weak var Lunch: UITextField!
    @IBOutlet weak var Dinner: UITextField!
    @IBOutlet weak var calorieSum: UILabel!

    
    func FoodCalorie() {
        var calBreakfast:Int
        var calLunch:Int
        var calDinner:Int
        
        if Breakfast.text!.isEmpty {
            calBreakfast = 0
        }else{
            calBreakfast = Int(Breakfast.text!)!
        }
        if Lunch.text!.isEmpty {
            calLunch = 0
        }else{
            calLunch = Int(Lunch.text!)!
        }
        if Dinner.text!.isEmpty {
            calDinner = 0
        }else{
            calDinner = Int(Dinner.text!)!
        }
        let totalCal = calBreakfast + calLunch + calDinner
        if totalCal < 2000 {
           calorieSum.text = String(totalCal)
        }else{
            
            let alert = UIAlertController(title:"Warning", message:"You shold take in less than 2000cal a day!", preferredStyle:UIAlertControllerStyle.alert)
            let cancelAction = UIAlertAction(title:"Cancel",style:UIAlertActionStyle.cancel, handler:nil)
            alert.addAction(cancelAction)
            let okAction = UIAlertAction(title:"OK",style:UIAlertActionStyle.default, handler:{ action in
                 self.calorieSum.text = String("Keep a balanced diet!")
            
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
       }
    }
    
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        FoodCalorie()
    }
    
    override func viewDidLoad() {
        Breakfast.delegate = self
        Lunch.delegate = self
        Dinner.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func onTypeGestureRecognized(_ sender: Any){
    Breakfast.resignFirstResponder()
    Lunch.resignFirstResponder()
    Dinner.resignFirstResponder()
        
        
    }

}

