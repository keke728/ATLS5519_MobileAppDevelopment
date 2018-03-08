//
//  CustomNavController.swift
//  Happy Ivy
//  Customize Navigationbar Color 
//  Created by Keke Wu on 3/7/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//  www.youtube.com/watch?v=Pjz_KU89FSY

import UIKit

class CustomNavController: UINavigationController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationBar.setBackgroundImage(UIImage(), for: .default)
        self.navigationBar.shadowImage = UIImage()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    



}
