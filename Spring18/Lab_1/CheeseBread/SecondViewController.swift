//
//  SecondViewController.swift
//  CheeseBread
//
//  Created by Keke Wu on 1/29/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBAction func gotoorder(_ sender: UIButton) {
        if(UIApplication.shared.canOpenURL(URL(string: "subway://")!)){
            UIApplication.shared.open(URL(string: "subway://")!, options:[:], completionHandler: nil)
        }else{
            if(UIApplication.shared.canOpenURL(URL(string: "yelp://")!)){
                UIApplication.shared.open(URL(string:"yelp://")!, options: [:], completionHandler: nil)
            }else{
            UIApplication.shared.open(URL(string:"http://www.subway.com")!, options: [:], completionHandler: nil)
        }
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

