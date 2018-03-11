//
//  WebViewController.swift
//  To-Do
//
//  Created by Keke Wu on 3/10/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit
import WebKit
class WebViewController: UIViewController, WKNavigationDelegate {

    var webpage: String?
  
    @IBOutlet var webView: WKWebView!
    @IBOutlet weak var webSpinner: UIActivityIndicatorView!
    override func viewDidLoad() {
        super.viewDidLoad()
        webView.navigationDelegate = self
        loadWebPage()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadWebPage(){
        guard let weburl = webpage
            else {
                print("no web found")
                return
        }
        let url = URL(string: weburl)
        let request = URLRequest(url: url!)
        webView.load(request)
    }
    
    
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        webSpinner.startAnimating()
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        webSpinner.stopAnimating()
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
