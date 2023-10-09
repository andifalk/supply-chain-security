![build](https://github.com/andifalk/supply-chain-security/actions/workflows/build.yml/badge.svg)
![snyk](https://github.com/andifalk/supply-chain-security/actions/workflows/snyk.yml/badge.svg)
![codeql](https://github.com/andifalk/supply-chain-security/actions/workflows/codeql.yml/badge.svg)
![publish](https://github.com/andifalk/supply-chain-security/actions/workflows/publish.yml/badge.svg)

# Supply Chain Security

Demos for software supply chain security



## IaC: Provision a kubernetes cluster

Next, we will provision a Kubernetes cluster on Google Cloud (GKE).

### Setup gcloud SDK

After you've installed the gcloud SDK, initialize it by running the following command.

`gcloud init`

This will authorize the SDK to access GCP using your user account credentials and add the SDK to your PATH. This steps requires you to login and select the project you want to work in. 

Finally, add your account to the Application Default Credentials (ADC). This will allow Terraform to access these credentials to provision resources on GCloud.

`gcloud auth application-default login`

### Terraform

In subfolder _iac_, you will find four files used to provision a VPC, subnets and a GKE cluster.

* __vpc.tf__ provisions a VPC and subnet. A new VPC is created for this tutorial so it doesn't impact your existing cloud environment and resources. This file outputs region.
* __gke.tf__ provisions a GKE cluster and a separately managed node pool (recommended). Separately managed node pools allows you to customize your Kubernetes cluster profile — this is useful if some Pods require more resources than others. You can learn more here. The number of nodes in the node pool is defined also defined here.
* __terraform.tfvars__ is a template for the project_id and region variables.
* __versions.tf__ sets the Terraform version to at least 0.14.

#### Update your terraform.tfvars file

Replace the values in your terraform.tfvars file with your project_id and region. Terraform will use these values to target your project when provisioning your resources. Your terraform.tfvars file should look like the following.

# terraform.tfvars
project_id = "REPLACE_ME"
region     = "us-central1"

You can find the project your gcloud is configured to with this command.

`gcloud config get-value project`

#### Initialize Terraform workspace

After you have saved your customized variables file, initialize your Terraform workspace, which will download the provider and initialize it with the values provided in your `terraform.tfvars` file.

`terraform init`

In your initialized directory, run terraform apply and review the planned actions. Your terminal output should indicate the plan is running and what resources will be created.

You can see this terraform apply will provision a VPC, subnet, GKE Cluster and a GKE node pool. Confirm the apply with a _yes_.

This process should take approximately 10 minutes. Upon successful application, your terminal prints the outputs defined in `vpc.tf` and `gke.tf`.

#### Configure kubectl

Now that you've provisioned your GKE cluster, you need to configure kubectl.

Run the following command to retrieve the access credentials for your cluster and automatically configure kubectl.

`gcloud container clusters get-credentials $(terraform output -raw kubernetes_cluster_name) --region $(terraform output -raw region)`

#### Clean up your workspace

The provisioned cluster has a pricing tag, so remember to destroy any resources you create once you are done with the demos. Run the destroy command and confirm with `yes` in your terminal.

`terraform destroy`
