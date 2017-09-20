//
//  ViewController.swift
//  what2eat
//
//  Created by Keke Wu on 9/9/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var foodText: UILabel!
    @IBOutlet weak var foodImage: UIImageView!
    @IBOutlet weak var fontSizeLabel: UILabel!
    @IBOutlet weak var foodControl: UISegmentedControl!
    @IBOutlet weak var capitalSwitch: UISwitch!
    @IBOutlet weak var colorControl: UISegmentedControl!
    
    @IBAction func textColor(_ sender: UISegmentedControl) {
        if colorControl.selectedSegmentIndex == 0 {
            foodText.textColor = UIColor.red
        }else if colorControl.selectedSegmentIndex == 1 {
            foodText.textColor = UIColor.blue
        }
    }

    func updateImage() {
        if foodControl.selectedSegmentIndex == 0 {
            foodText.text = "Sushi tastes good!"
            foodImage.image = UIImage(named:"Sushi")
        }else if foodControl.selectedSegmentIndex == 1 {
            foodText.text = "Enjoy your steak!"
            foodImage.image = UIImage(named:"Steak")
        }else if foodControl.selectedSegmentIndex == 2 {
            foodText.text = "Salad is healthy!"
            foodImage.image = UIImage(named:"Salad")
        }else if foodControl.selectedSegmentIndex == 3 {
            foodText.text = "Here is your soup!"
            foodImage.image = UIImage(named:"Soup")
        }
    }
    
    func updateCaps() {
        if capitalSwitch.isOn {
            foodText.text = foodText.text?.uppercased()
        }else{
            foodText.text = foodText.text?.lowercased()
        }
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func updateFont(_ sender: UISwitch) {
        updateCaps()
    }
    
    @IBAction func changeFood(_ sender: UISegmentedControl) {
        updateImage()
        updateCaps()
    }
    
    @IBAction func changeFontSize(_ sender: UISlider) {
        let fontSize = sender.value
        fontSizeLabel.text = String(format:"%.0f", fontSize)
        let fontSizeCGFloat = CGFloat(fontSize)
        foodText.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
