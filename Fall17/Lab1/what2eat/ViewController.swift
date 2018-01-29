//
//  ViewController.swift
//  what2eat
//
//  Created by Keke Wu on 9/9/17.
//  Copyright © 2017 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var textMessage: UILabel!
    @IBOutlet weak var foodImage: UIImageView!
    @IBAction func chooseFood(_ sender: UIButton) {
        if sender.tag == 1 {
            foodImage.image = UIImage(named: "Sushi")
            textMessage.text = "Enjoy your Sushi!"
        }
        else if sender.tag == 2 {
            foodImage.image = UIImage(named: "Steak")
            textMessage.text = "Good Choice!"
        }
        else if sender.tag == 3 {
            foodImage.image = UIImage(named: "Salad")
            textMessage.text = "Have a good meal!"
        }
        else if sender.tag == 4 {
            foodImage.image = UIImage(named: "Soup")
            textMessage.text = "Anything else?"
        }
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

