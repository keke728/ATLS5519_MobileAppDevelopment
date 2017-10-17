//
//  ViewController.swift
//  Daylight
//
//  Created by Keke Wu on 10/14/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
   
    @IBAction func goodChoose(_ sender: UIButton) {
        if let buttonTitle = sender.title(for: .normal) {
            print(buttonTitle)
        }
    }
    @IBAction func okayChoose(_ sender: UIButton) {
        if let buttonTitle1 = sender.title(for: .normal) {
            print(buttonTitle1)
        }
    }
    @IBAction func badChoose(_ sender: UIButton) {
        if let buttonTitle2 = sender.title(for: .normal) {
            print(buttonTitle2)
        }
    }
 
    override func prepare(for segue:UIStoryboardSegue, sender:Any?){
        
       let button = sender as! UIButton
        let controller = segue.destination as! FoodViewController
        controller.name = button.currentTitle
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

