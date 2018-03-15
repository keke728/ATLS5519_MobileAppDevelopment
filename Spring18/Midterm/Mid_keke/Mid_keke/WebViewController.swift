//
//  WebViewController.swift
//  Mid_keke
//
//  Created by Keke Wu on 3/15/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit
import WebKit

class WebViewController: UIViewController, UIWebViewDelegate {

    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var spinner: UIActivityIndicatorView!
    
    var name = String()
    
    func configureView() {
        if name == "Luciles" {
            loadWebPage("https://www.luciles.com/")
            
        }
        if name == "Rincon Argentina" {
            loadWebPage("https://www.rinconargentinoboulder.com//")
        }
        if name == "Backcountry Pizza" {
            loadWebPage("https://www.backcountrypizzaandtaphouse.com/")
            
        }
        if name == "West End Tavern" {
            loadWebPage("https://www.thewestendtavern.com/")
        }
    }
    
    func loadWebPage(_ urlString: String){
        //the urlString should be a propery formed url
        //creates a NSURL object
        let url = URL(string: urlString)
        //create a NSURLRequest object
        let request = URLRequest(url: url!)
        //load the NSURLRequest object in our web view
        webView.load(request)
    }
    
    //UIWebViewDelegate method that is called when a web page begins to load
    func webViewDidStartLoad(_ webView: UIWebView) {
        print("start load")
        spinner.startAnimating()
    }
    
    //UIWebViewDelegate method that is called when a web page loads successfully
    func webViewDidFinishLoad(_ webView: UIWebView) {
        print("stop load")
        spinner.stopAnimating()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureView()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
